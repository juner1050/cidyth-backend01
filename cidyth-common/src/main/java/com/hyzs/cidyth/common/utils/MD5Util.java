package com.hyzs.cidyth.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */
public class MD5Util {

    private static String PW = "";
    private static String SPID = "";

    /**
     * MD5加密
     * @param rand
     * @return
     * @throws NoSuchAlgorithmException
     * @author HYQ
     * @date Feb 5, 2016 9:40:21 AM
     */
    public static String md5(String rand) throws NoSuchAlgorithmException {

        String msg = rand ;

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(msg.getBytes());
        byte[] digest =  messageDigest.digest();

        String new_pw = byte2hex(digest);

        return new_pw;
    }
    /**
     * byte数组转十六进制字符串
     * @param b
     * @return
     * @author HYQ
     * @date 2015-3-26 下午06:05:29
     */
    public static String byte2hex(byte[] b){
        StringBuilder buf = new StringBuilder();

        for (byte c : b) {
            buf.append(String.format("%02x", c).toUpperCase());
        }

        return buf.toString();
    }


    private static String CODE = "gbk";

    /**
     * base64加密
     * @param str
     * @return
     * @author HYQ
     * @date Feb 5, 2016 9:41:27 AM
     */
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes(CODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /**
     * base64解密
     * @param s
     * @return
     * @author HYQ
     * @date Feb 5, 2016 9:41:40 AM
     */
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }







}
