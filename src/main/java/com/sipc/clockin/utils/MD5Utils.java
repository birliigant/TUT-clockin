package com.sipc.clockin.utils;

import java.security.MessageDigest;

public class MD5Utils {

    public static String encrypt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 0xff) + 0x100).substring(1));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
