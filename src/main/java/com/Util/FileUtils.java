package com.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//文件处理工具类
public class FileUtils {

    // 得到相对路径
    public static String getRelativePath(File baseDir,File file) {
        if(baseDir.equals(file))
            return "";
        if(baseDir.getParentFile() == null)
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length()+1);
    }

    //查询某个目录下的所有文件
    public static List<File> searchAllFile(File dir) throws IOException {
        ArrayList arrayList = new ArrayList();
        searchFiles(dir,arrayList);
        return arrayList;
    }

    //递归获取某个目录下的所有文件
    public static void searchFiles(File dir,List<File> collector) throws IOException {
        if(dir.isDirectory()) {
            File[] subFiles = dir.listFiles();
            for(int i = 0; i < subFiles.length; i++) {
                searchFiles(subFiles[i],collector);
            }
        }else{
            collector.add(dir);
        }
    }

    //创建文件
    public static File mkdir(String dir,String file) {
        if(dir == null) throw new IllegalArgumentException("dir must be not null");
        File result = new File(dir,file);
        if(result.getParentFile() != null) {
            result.getParentFile().mkdirs();
        }
        return result;
    }

    public static Boolean deleteFile(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            return false;
        }
        //获取目录下子文件
        File[] files = file.listFiles();

        //遍历该目录下的文件对象
        assert files != null;
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                deleteFile(f);
            } else {
                //文件删除
                f.delete();
                //打印文件名
            }
        }
        //文件夹删除
        file.delete();
        return true;
    }


}
