package com.Config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
@Slf4j
public class SQLConfig {

@Bean
    public Properties getProper(){
    try {
        File file = new File("config/TypeName.properties");
        Properties properties=new Properties();
        FileInputStream inputStream=new FileInputStream(file);
        properties.load(inputStream);
        return properties;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
}
