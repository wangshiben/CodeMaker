package com.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private String KeyName;//主键名称
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

}
