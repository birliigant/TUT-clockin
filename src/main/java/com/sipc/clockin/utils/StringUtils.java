package com.sipc.clockin.utils;

public class StringUtils {

    public static final String EMPTY = "";

    private static boolean empty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(String str) {
        return empty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !empty(str);
    }

}
