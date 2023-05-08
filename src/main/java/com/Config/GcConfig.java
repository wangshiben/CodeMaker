package com.Config;

import com.Util.FileUtils;
import com.Util.SessionMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

@Configuration
@EnableScheduling//开启定时任务
@Slf4j
public class GcConfig {
    @Autowired
    @Qualifier("getProper")
    private Properties properties;

    public static String testWord="1111";


//    @Scheduled(cron = "0/31 * * * * ?")
//    public void Refersh(){
//        testWord=(Math.random()*10)+"";
//        log.info("定时void更新test->{}",testWord);
//    }
//    @Scheduled(cron = "0/10 * * * * ? ")
//    public void MakeTestWord(){
//        testWord="aaaaa";
//        log.info("更新test->{}",testWord);
//    }




    @Scheduled(cron = "0 0 0/1 * * ?")
    public void RemoveOutTimeTask(){//每隔一小时监控session
        Map<String, HttpSession> map = SessionMapUtil.getMap();
        if (Boolean.parseBoolean(properties.getProperty("online"))){
        Set<String> keys = map.keySet();//获取所有Session
        for (String key : keys) {
            HttpSession httpSession = map.get(key);
            if (httpSession.getLastAccessedTime()-httpSession.getCreationTime()==3600L){
                String outPath = properties.getProperty("outPath")+"\\\\"+key;
                File file1 =new File(outPath);
                String absolutePath1 = file1.getAbsolutePath();//获取输出名称的绝对路径,不然会报错
                file1=new File(absolutePath1);
                Boolean aBoolean = FileUtils.deleteFile(file1);
                log.info("删除已过期的ID:{},结果{}",key,aBoolean);
                map.remove(key);
                httpSession.invalidate();

            }
        }
        }

    }



}
