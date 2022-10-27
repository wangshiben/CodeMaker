package com.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
public class Table {
    private String KeyName;//主键名称
    private String UpperTable;//表名大写
    private String TableName;//表名(实例名称)
    private List<Column> columns;
    public String getKeyType(){
       for (Column column : columns) {
           if (column.getColumnName().equals(KeyName)){
               return column.getColumnType();
           }
       }
       return null;
    }

    public Table(String keyName, String tableName, List<Column> columns) {
        StringBuilder sb=new StringBuilder();
        for (String s : tableName.split("_")) {
            s=UpperKeyName(s);
            sb.append(s);
        }
        UpperTable=sb.toString();
        KeyName = keyName;
        TableName = tableName;
        this.columns = columns;
    }
    private String UpperKeyName(String names){
        return names.substring(0,1).toUpperCase()+names.substring(1);
    }


}
