/**
 * Copyright (C) 2011 by BESTRUN CO.,LTD.
 * 
 * @Title: StringUtil.java
 * @Package: com.bestrun.android.utils
 * @Description: 字符串相关的帮助工具类
 * @author: Yang
 * @date: 2011-10-8 15:39:37
 * @version: 1.0
 */
package com.njcool.lzccommon.utils;

import java.util.List;
import java.util.regex.Pattern;

public class CoolStringUtil {
    public static final String EMPTY = "";
    
    /**
     * 检查字符串是否为空，包括NULL、空串和只有空格的字符串
     *
     * @param x
     * @return
     */
    public static boolean isEmpty(String x) {
        return x == null || x.trim().length() == 0;
    }

    /**
     * 
     * 判断字符串是否为纯数字[0-9\.]
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
    	return isNumeric(str, false);
    }

    /**
     * 
     * 判断字符串是否为纯数字[0-9\.]，包含正负数。
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str, boolean includeNagetive) {
    	String strPattern = "^\\d+(\\.\\d+)?$";
    	if(includeNagetive) {
    		strPattern = "^[+-]?\\d+(\\.\\d+)?$";
    	}
        Pattern pattern = Pattern.compile(strPattern);
        return pattern.matcher(str).matches();
    }

    /**
     * 获得以字节为单位的字符串长度
     * 
     * @param s
     * @return
     */
    public static int getWordCount(String s) {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 将字符串列表转化为一个字符串, 用@param<sep>分隔
     * 
     * @param stringList
     *         待转化的字符串列表
     * @param sep
     *         分隔符
     * @param addQuotar
     *         是否需要给元素添加引号，默认为true
     * @return
     */
    public static String listToString(List<String> stringList, String sep, boolean addQuotar) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(sep);
            } else {
                flag = true;
            }
            if(addQuotar) {
                result.append("'").append(string).append("'");
            } else {
                result.append(string);
            }
        }
        return result.toString();
    }

    /** 
     * 将字符串列表转化为一个字符串, 用@param<sep>分隔
     *
     * @param stringList
     *         待转化的字符串列表
     * @param sep
     *         分隔符
     * @return
     */
    public static String listToString(List<String> stringList, String sep) {
        return listToString(stringList, sep, true);
    }
    
    /**
     * 拼凑URL字符串
     *
     * @param url1
     *         第一个URL
     * @param url2
     *         第二个URL
     * @return
     */
    public static String combineUrl(String url1, String url2) {
        String sep = "/";
        boolean hasEnd = url1.endsWith(sep);
        boolean hasStart = url2.startsWith(sep);
        if(hasEnd && hasStart) {
            return url1.concat(url2.substring(1));
        } else if(!hasEnd && !hasStart) {
            return url1.concat(sep).concat(url2);
        } else {
            return url1.concat(url2);
        }
    }

}
