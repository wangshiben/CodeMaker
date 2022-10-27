package com.Make;

import com.Util.FileUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Center {

    private String templatePath;//模板路径
    private String outPath;//代码生成路径
    private Configuration cfg;

    public Center(String templatePath, String outPath) throws IOException {
        this.templatePath = templatePath;
        this.outPath = outPath;
        //实例化Configuration对象
        cfg = new Configuration();
        //指定模板加载器
        FileTemplateLoader ftl = new FileTemplateLoader(new File(templatePath));
        cfg.setTemplateLoader(ftl);
    }
    /**
        * 代码生成
     *      1.扫描模板路径下的所有模板
     *      2.对每个模板进行文件生成（数据模型）
            */
    public void scanAndGenerator(Map<String,Object> dataModel) throws IOException, TemplateException {//扫描并生成模板文件
        //1.根据模板路径找到此路径下的所有模板文件
        File files = new File(templatePath);

        List<File> fileList = FileUtils.searchAllFile(files);
        //2.对每个模板进行文件生成
        for (File file : fileList) {
            executeGenertor(dataModel,file);
        }
    }

    /**
     * 对模板进行文件生成
     * @param dataModel ： 数据模型
     * @param file      ： 模板文件
     *            模板文件：c：com.ihrm.system.abc.java
     */
    private void executeGenertor(Map<String,Object> dataModel,File file) throws IOException, TemplateException {
        //1.文件路径处理   (E:\模板\${path1}\${path2}\${path3}\${ClassName}.java)
        //templatePath : E:\模板\
        String templateFileName = file.getAbsolutePath().replace(this.templatePath,"");
        String outFileName = processTemplateString(templateFileName,dataModel);
        //2.读取文件模板
        Template template = cfg.getTemplate(templateFileName);
        template.setOutputEncoding("utf-8");//指定生成文件的字符集编码
        //3.创建文件
        File file1 = FileUtils.mkdir(outPath, outFileName);
        //4.模板处理（文件生成）
        FileWriter fw = new FileWriter(file1);
        template.process(dataModel,fw);
        fw.close();
    }


    public String processTemplateString(String templateString,Map dataModel) throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        Template template = new Template("ts",new StringReader(templateString),cfg);
        template.process(dataModel,out);
        return out.toString();
    }





}
