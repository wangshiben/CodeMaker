package com.Servlet;

import com.Make.Center;
import com.Pojo.Column;
import com.Pojo.Table;
import com.Pojo.DBCConnection;
import com.Util.ChangeTypeName;
import com.Util.JDBCutil;
import freemarker.template.TemplateException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Controller
@Slf4j
public class IndexServlet {
    @Autowired
    @Qualifier("getProper")
    private Properties properties;

    @RequestMapping("/")
    public String Index(){
        return "index";
    }
    @RequestMapping("/getConnection")
    public String Connect(String URL, DBCConnection connection, HttpServletRequest request){
        Connection connections=null;
        try {
            connections = JDBCutil.getConnection(URL,connection);
        } catch (SQLException e) {
            request.setAttribute("message",e.getMessage());
            log.error(e.getMessage());
            return "redirect:/";
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
            try {
                assert connections != null;
                DatabaseMetaData metaData = connections.getMetaData();
                HttpSession session = request.getSession();
                session.setAttribute("metaData",metaData);
                ResultSet catalogs = metaData.getCatalogs();
                List<String> dbs=new ArrayList<>();
                while (catalogs.next()){//获取数据库名称
                    dbs.add(catalogs.getString(1));
                }
                request.setAttribute("dataBase",dbs);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return "ChooseDB";
    }

    @RequestMapping("/DBchoose")
    public String ChooseTable(String DataBase,HttpServletRequest request,String PackageName){//根据选择的数据库名称选择表
        try {
            HttpSession session = request.getSession();
            session.setAttribute("BaseName",DataBase);
            session.setAttribute("PackageName",PackageName);
            DatabaseMetaData metaData = (DatabaseMetaData) session.getAttribute("metaData");
            ResultSet tables = metaData.getTables(DataBase, null, null, new String[]{"TABLE"});
            List<String> list=new ArrayList<>();
            while (tables.next()){
                list.add(tables.getString("TABLE_NAME"));
            }
            session.setAttribute("colums",tables);//放入session域中
            request.setAttribute("colums",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            request.setAttribute("message","连接失败:服务器异常");
        }
        return "TableChoose";
    }
    @RequestMapping("/TableChoose")
    public String GetTable(String Table,HttpSession session,HttpServletRequest request){//获取字段名
        DatabaseMetaData metaData = (DatabaseMetaData) session.getAttribute("metaData");
        String baseName = (String) session.getAttribute("BaseName");
        try {
            ResultSet columns = metaData.getColumns(baseName, null, Table, null);
            String dataBaseName = (String) session.getAttribute("BaseName");
            ResultSet primaryKeys = metaData.getPrimaryKeys(dataBaseName, null, Table);//获取主键结果集
            List<String> list=new ArrayList<>();//字段名称集
            List<Column> lists=new ArrayList<>();

            while (columns.next()){
                lists.add(new Column(columns.getString("COLUMN_NAME"),ChangeTypeName.Change(columns.getString("TYPE_NAME"),properties)));
                list.add(columns.getString("COLUMN_NAME"));
            }
            request.setAttribute("cloum_name",list);

            String column_name;
            if (primaryKeys.next()){
                //获取主键名称，若没有则要手动选择
            column_name = primaryKeys.getString("COLUMN_NAME");
            log.info(column_name);
            }
            else {
                request.getSession().setAttribute("column",lists);//将数据存入session中
                session.setAttribute("Table",Table);
                return "CloumnChoose";
            }
            assert column_name!=null;
            com.Pojo.Table cloum=new Table(column_name,Table,lists);//获取到完整对象
            String packageName = (String) session.getAttribute("PackageName");//获取到包名
            //调用生成方法
            Map<String,Object> model=new HashMap<>();
            model.put("PackageName",packageName);
            model.put("TableName",cloum.getTableName());
            model.put("ColumName",cloum.getColumns());
            model.put("DBName",dataBaseName);
            model.put("KeyName",cloum.getKeyName());
            model.put("KeyType",cloum.getKeyType());
//            columns.getString();
            Center center=new Center(properties.getProperty("templatePath"),properties.getProperty("outPath"));
            center.scanAndGenerator(model);//生成
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (IOException | TemplateException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
    @RequestMapping("/mainKey")
    public String MakeCodes(String mainKey,HttpServletRequest request,HttpSession session){
        String table = (String) session.getAttribute("Table");
        String dataBaseName = (String) session.getAttribute("BaseName");
        String packageName = (String) session.getAttribute("PackageName");//获取到包名
        List<Column> lists = (List<Column>) session.getAttribute("column");
        Table cloum=new Table(mainKey,table,lists);
        Map<String,Object> model=new HashMap<>();
        model.put("PackageName",packageName);
        model.put("TableName",cloum.getTableName());
        model.put("ColumName",cloum.getColumns());
        model.put("DBName",dataBaseName);
        model.put("KeyName",cloum.getKeyName());
        model.put("KeyType",cloum.getKeyType());
//            columns.getString();
        try {
            Center center=new Center(properties.getProperty("templatePath"),properties.getProperty("outPath"));
            center.scanAndGenerator(model);//生成
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info(cloum.toString());
        return "redirect:/";
    }


}
