package com.Test;


import com.Entity.mailEntity;
import com.Make.Center;
import com.Pojo.Column;
import com.Pojo.DBCConnection;
import com.Pojo.Table;
import com.Resp.BaseRespones;
import com.Service.MailService;
import com.Servlet.InterFaceController;
import com.Util.JDBCutil;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.*;

@SpringBootTest
@Slf4j
public class CodeTest {
    @Autowired
    private DataSource dataSource;
    @Test
    public void Test01(){
        try {

            Connection connection= JDBCutil.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            ResultSet catalogs = metaData.getCatalogs();
            System.out.println("数据库名称************************");
            while (catalogs.next()){
                //获取所有数据库名称
                System.out.println(catalogs.getString(1));
            }
            System.out.println("(((((((((((((((((((((())))))))))))))))))))))))");

            System.out.println("表名称*************************");
            System.out.println("当前数据库:parttimejob");
            ResultSet columns = metaData.getTables("parttimejob", null, null, new String[]{"TABLE"});

            while (columns.next()){
                String table_name = columns.getString("TABLE_NAME");
                System.out.println("tableName="+table_name);
                System.out.println("列名***************");
                ResultSet columns1 = metaData.getColumns("parttimejob", null, table_name, null);

                System.out.println("_____________________________________________");
                while (columns1.next()){
                    System.out.println(columns1.getString("TYPE_NAME"));

                }
            }

            connection.close();
            catalogs.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void DataBaseTest(){

        try {
            Connection connection= JDBCutil.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns1 = metaData.getColumns("parttimejob", null, "post", null);
            
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test04() throws Exception {
        Connection connection = JDBCutil.getConnection();
        System.out.println(connection);
    }

    @Test
    public void Test02(){
        //创建Configuration类
        Configuration configuration=new Configuration();
        //创建类加载器
        //文件路径加载器
        try {
            FileTemplateLoader fileTemplateLoader=new FileTemplateLoader(new File("Templates"));
            configuration.setTemplateLoader(fileTemplateLoader);//将路径加载器放入配置类中
            Template template = configuration.getTemplate("${name}.ftl");
            //构建数据模型
            Map<String,Object> map=new HashMap<>();
            map.put("userName","你好世界");
            template.process(map, new  BufferedWriter(new FileWriter("测试程序.txt")));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test03() throws Exception {
        Center center=new Center("D:\\CodeMaker\\Templates","D:\\CodeMaker\\outPut");
        Map<String,Object> map=new HashMap<>();
        map.put("PackageName","com");
        List<Column> columns=new ArrayList<>();
        columns.add(new Column("Post","String"));
        columns.add(new Column("ID","int"));
        Table table=new Table("Post","Post",columns);
        map.put("TableName","Table");
        map.put("ColumName",columns);
        map.put("DBName","parttimejob");
        map.put("KeyName","Post");
        map.put("KeyType","String");

        center.scanAndGenerator(map);
    }
    @Test
    public void Test05() throws IOException, SQLException, ClassNotFoundException {
        Connection connection = JDBCutil.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys("parttimejob", null, "user");
        primaryKeys.next();
        String column_name = primaryKeys.getString("COLUMN_NAME");
        System.out.println(column_name);
    }
    @Test
    public void Test07(){
//        Column column =new Column("a","b");
        System.out.println(new DBCConnection().toString());
    }
    @Autowired
    private MailService mailService;
    @Test
    public void Test08(){
        mailEntity mailEntity=new mailEntity("3325118363@qq.com","Test","test",null);
        mailService.sendHtmlMail(mailEntity);
    }
    @Test
    public void Test09(){
        String code = UUID.randomUUID().toString();
        String[] split = code.split("-");
        System.out.println(Arrays.toString(split));

    }
    @Autowired
    @Qualifier("getProper")
    private Properties properties;
    @Test
    public void Test10(){
        String templatePath = properties.getProperty("templatePath");
        File file=new File(templatePath);
        String path = file.getAbsolutePath();
        System.out.println(path);
    }


    @Test
    public void Test11(){
        try {
            FileOutputStream outputStream=new FileOutputStream("测试.zip");
            InterFaceController.MakeZIP(properties,outputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }






}
