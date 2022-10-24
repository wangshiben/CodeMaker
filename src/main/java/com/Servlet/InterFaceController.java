package com.Servlet;

import com.Make.Center;
import com.Pojo.Column;
import com.Pojo.DBCConnection;
import com.Pojo.Table;
import com.Resp.BaseRespones;
import com.Util.ChangeTypeName;
import com.Util.JDBCutil;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TransferQueue;

@RestController
@Slf4j
public class InterFaceController {
    @Autowired
    @Qualifier("getProper")
    private Properties properties;
    @Value("${spring.datasource.url}")
    String url;//数据库地址
    @Value("spring.datasource.username")
    String userName;//用户名
    @Value("${spring.datasource.password}")
    String password;

    @GetMapping("/isconnect")
    public BaseRespones<Boolean> IsConnect(HttpSession session){
        if (url==null||userName==null||password==null){
        return BaseRespones.failed("数据库连接失败:未设置数据库参数",false);
        }else {
            Connection connection=null;
            try {
                connection = JDBCutil.getConnection(url, new DBCConnection(userName, password));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                return BaseRespones.failed("连接失败:参数错误", false);
            }
            DatabaseMetaData metaData;
            try {
                metaData = connection.getMetaData();
            } catch (SQLException e) {
                log.error(e.getMessage());
                return BaseRespones.failed("获取数据库元数据失败",false);
            }
            session.setAttribute("metaData",metaData);
            return BaseRespones.success("连接数据库成功");
        }
    }
    @PostMapping("/connect")
    public BaseRespones<Boolean> Connect(HttpSession session, @RequestBody String url,@RequestBody String user,@RequestBody String password){//手动获取连接
        Connection connection=null;
        try {
            connection = JDBCutil.getConnection(url, new DBCConnection(user, password));
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            return BaseRespones.failed("连接失败:数据库参数错误",false);
        }
        DatabaseMetaData metaData;
        try {
            metaData  = connection.getMetaData();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return BaseRespones.failed("获取数据库元数据失败",false);
        }
        session.setAttribute("metaData",metaData);
        return BaseRespones.success("数据库连接成功", true);
    }
    @GetMapping("/database/get_db")
    public BaseRespones<List<String>> GetAllDB(HttpSession session){//获取所有数据库名称
        DatabaseMetaData metaData = (DatabaseMetaData) session.getAttribute("metaData");
        List<String> lists=new ArrayList<>();
        try {
            ResultSet catalogs = metaData.getCatalogs();
            while (catalogs.next()){//获取数据库名称
                lists.add(catalogs.getString(1));
            }
        } catch (SQLException e) {
            log.error("获取所有列名称失败"+e.getMessage());
            return BaseRespones.failed("获取所有数据库数据失败");
        }
        return BaseRespones.success("获取所有数据成功",lists);
    }
    @GetMapping("/database/get_all")
    public BaseRespones<List<String>> GetAllTable(HttpSession session,String database){//获取所有数据库中的所有表名称
        DatabaseMetaData metaData = (DatabaseMetaData) session.getAttribute("metaData");
        session.setAttribute("basename",database);
        ResultSet tables;
        List<String> list=new ArrayList<>();
        try {
            tables = metaData.getTables(database, null, null, new String[]{"TABLE"});
            while (tables.next()){
                list.add(tables.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return BaseRespones.success("获取成功",list);
    }
    @GetMapping("/makeCode")//生成文件接口(有主键策略)
    public BaseRespones<Object> CodeMake(HttpSession session,String table_name,String package_name){//生成代码
        DatabaseMetaData metaData = (DatabaseMetaData) session.getAttribute("metaData");//获取元数据
        String basename = (String) session.getAttribute("basename");//选中数据库的名称
        String keyName=null;
        List<String> list=new ArrayList<>();//字段名称集
        List<Column> lists=new ArrayList<>();//结果
        try {
            ResultSet primaryKeys = metaData.getPrimaryKeys(basename, null, table_name);//获取主键名称
            ResultSet columns = metaData.getColumns(basename, null, table_name, null);//获取列的名称
            while (columns.next()){
                list.add(columns.getString("COLUMN_NAME"));
                lists.add(new Column(columns.getString("COLUMN_NAME"), ChangeTypeName.Change(columns.getString("TYPE_NAME"),properties)));
            }
            if (primaryKeys.next()){
                //获取主键名称，若没有则要手动选择
                keyName = primaryKeys.getString("COLUMN_NAME");//
            }else {
                session.setAttribute("column",lists);//表中的列名放在session域中
                session.setAttribute("tablename",table_name);//选中的列名+类型放在session域中
                session.setAttribute("package_name",package_name);
                return new BaseRespones<>(300,"无主键,请选择一键作为主键",new Date(System.currentTimeMillis()).toString(),list);
            }
         MakeCode(keyName,table_name,lists,package_name,basename);
        } catch (SQLException | TemplateException | IOException e) {
            log.error(e.getMessage());
            return BaseRespones.failed("服务器错误");
        }
        return BaseRespones.success("生成成功",true);

    }

    @GetMapping("/false_name")
    public BaseRespones<Boolean> False_Email(String key_name,HttpSession session){
        String basename = (String) session.getAttribute("basename");
        List<Column> column = (List<Column>) session.getAttribute("column");
        String tablename = (String) session.getAttribute("tablename");
        String package_name = (String) session.getAttribute("package_name");
        try {
            MakeCode(key_name,tablename,column,package_name,basename);
        } catch (IOException | TemplateException e) {
            log.error("生成代码出错:"+e.getMessage());
            return BaseRespones.failed("生成代码出错:"+e.getMessage());
        }
        return BaseRespones.success("生成代码成功请在"+properties.getProperty("outPath")+"下查看");
    }
    //生成文件接口
    private void MakeCode(String keyName,String tableName,List<Column> columns,String package_name,String basename) throws IOException, TemplateException {
        Table tables=new Table(keyName,tableName,columns);
        Map<String,Object> model=new HashMap<>();
        model.put("PackageName",package_name);
        model.put("TableName",tables.getTableName());
        model.put("ColumName",tables.getColumns());
        model.put("DBName",basename);
        model.put("KeyName",tables.getKeyName());
        model.put("KeyType",tables.getKeyType());
        Center center=new Center(properties.getProperty("templatePath"),properties.getProperty("outPath"));
        center.scanAndGenerator(model);//生成
    }
//










}
