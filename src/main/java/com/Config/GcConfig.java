package com.Config;

import com.Util.FileUtils;
import com.Util.SessionMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableScheduling//开启定时任务
@Slf4j
public class GcConfig {
    @Autowired
    @Qualifier("getProper")
    private Properties properties;

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
