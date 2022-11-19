package com.Util;

import com.Make.Center;
import com.Pojo.Column;
import com.Pojo.Table;
import freemarker.template.TemplateException;
import org.slf4j.Logger;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class MakerUtils {
    //生成文件接口
    public static void  MakeCode(String keyName, String tableName, List<Column> columns, String package_name, String basename, Properties properties, Logger log) throws IOException, TemplateException {
        Table tables=new Table(keyName,tableName,columns);
        Map<String,Object> model=new HashMap<>();
        model.put("PackageName",package_name);//生成包名
        model.put("TableName",tables.getTableName());//生成表的名字(实体类)
        model.put("ColumName",tables.getColumns());//列的名字(实体类中参数)
        model.put("DBName",basename);//数据库名字
        model.put("KeyName",tables.getKeyName());//主键名字
        model.put("UpperKeyName", CharsUtil.UpperFirstCode(tables.getKeyName()));//首字母大写,方便调用Get/Set方法
        model.put("KeyType",tables.getKeyType());//主键类型
        model.put("UpperTable",tables.getUpperTable());//大写
        String templatePath = properties.getProperty("templatePath");
        File file=new File(templatePath);
        String absolutePath = file.getAbsolutePath();//获取模板名称的绝对路径名称,不然会报错
        String outPath = properties.getProperty("outPath");
        File file1 =new File(outPath);
        String absolutePath1 = file1.getAbsolutePath();//获取输出名称的绝对路径,不然会报错
        log.info(file.getAbsolutePath());//打印模板的绝对路径
        Center center=new Center(absolutePath,absolutePath1);
        center.scanAndGenerator(model);//生成
    }
    //生成ZIP文件
    public static void MakeZIP(Properties properties, OutputStream outputStream) throws IOException {//生成ZIP包,前提:未改变文件位置
        byte[]data=new byte[1024];
        String outPath = properties.getProperty("outPath");
        File root=new File(outPath);
        outPath=root.getAbsolutePath();
        List<File> list=new LinkedList<>();
//        FileOutputStream outputStream=new FileOutputStream("测试.zip");
        FileUtils.searchFiles(root,list);//生成集合
        ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream);
        BufferedInputStream inputStream=null;
        for (File file : list) {
            inputStream =new BufferedInputStream(new FileInputStream(file));
            String replace = file.getAbsolutePath();
            String s = replaceAbs(outPath, replace);
            ZipEntry entry=new ZipEntry(s);
            zipOutputStream.putNextEntry(entry);//添加实体
            while (inputStream.read(data)>0){
                zipOutputStream.write(data);
            }
            inputStream.close();//关闭Input流
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }
    private static String replaceAbs(String origin,String replaceMeant){
        String[] split = origin.split("\\\\");
        String[] split1 = replaceMeant.split("\\\\");
        int index=0;
        for (; index < split.length; index++) {
            if (!split[index].equals(split1[index])){
                break;
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = index; i < split1.length; i++) {
            sb.append("\\\\").append(split1[i]);
        }
        return sb.toString();


    }
}
