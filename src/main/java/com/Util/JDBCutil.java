package com.Util;

import com.Pojo.DBCConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCutil {
    public static Connection getConnection() throws SQLException,ClassNotFoundException{
        String urls="com.mysql.cj.jdbc.Driver";
        String URL="jdbc:mysql://?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String userNme="root";
        String password="13qe4r5t";
        Properties properties=new Properties();
        properties.put("user",userNme);
        properties.put("password",password);
            Class.forName(urls);
            return DriverManager.getConnection(URL,properties);

    }
    public static Connection getConnection(String URL, DBCConnection connection) throws SQLException,ClassNotFoundException{
        String urls="com.mysql.cj.jdbc.Driver";
        Properties properties=new Properties();
        properties.put("user",connection.getUser());
        properties.put("password",connection.getPassword());
        Class.forName(urls);
        return DriverManager.getConnection(URL,properties);

    }
}
