package com.Util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionMapUtil {
    private static Map<String, HttpSession> map=new HashMap<>();
    public static synchronized void put(String key,HttpSession val){
        map.put(key,val);
    }
    public static HttpSession get(String key){
        return map.get(key);
    }
    public static Map<String, HttpSession> getMap(){
        return map;
    }
    public static HttpSession getSession(String key,HttpSession session){
        if (key==null){
            return session;
        }
        HttpSession httpSession = map.get(key);
        return httpSession==null? session: httpSession;
    }



}
