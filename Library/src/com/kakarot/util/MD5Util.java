package com.kakarot.util;

import java.security.MessageDigest;

public class MD5Util {
    public static String MD5(String s) {
        if(s!=null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] bytes = md.digest(s.getBytes("utf-8"));
                return toHex(bytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
    
    public static boolean checkMD5(String pwd,String md5Pwd){
    	return md5Pwd.equals(MD5(pwd));
    }
}
