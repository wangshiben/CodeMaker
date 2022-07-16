package com.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ChangeTypeName {


    public static String Change(String TypeName,Properties properties) throws IOException {
//        Properties properties=new Properties();
//        FileInputStream fileInputStream=new FileInputStream(new File("config/TypeName.properties"));
//        this.properties.load(fileInputStream);
        String property = properties.getProperty(TypeName);
        return property;
    }
}
