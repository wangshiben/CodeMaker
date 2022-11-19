package com.Util;

public class CharsUtil {//对字符串进行大小写操作
    public static String UpperFirstCode(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] = toUpperCase(chars[0]);
        return String.valueOf(chars);
    }


    private static char toUpperCase(char c) {
        if (97 <= c && c <= 122) {
            c ^= 32;
        }
        return c;
    }
}
