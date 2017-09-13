package com.shangxiaom.commonlist.utils;

public class StringUtil {

    public static boolean isEmpty(String src) {
        if (src != null && !"".equals(src.trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static String getMiddle(String xml, String start, String end) {
        xml = xml.replaceAll("\r", "").replaceAll("\n", "");
        if (xml.contains(start) && xml.contains(end)) {
            return getMiddle(xml.substring(xml.indexOf(start) + start.length(), xml.lastIndexOf(end)), start, end);
        } else {
            return xml;
        }
    }
}