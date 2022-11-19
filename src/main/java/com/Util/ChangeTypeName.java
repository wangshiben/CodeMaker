package com.Util;


import java.io.IOException;
import java.util.Properties;

public class ChangeTypeName {


    public static String Change(String TypeName,Properties properties) throws IOException {
        String property = properties.getProperty(TypeName);
        return property;
    }
}
