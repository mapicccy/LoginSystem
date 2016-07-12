package com.guanjun;

import sun.misc.BASE64Encoder;
import java.security.MessageDigest;

/**
 * Created by guanjun on 2016/7/12.
 * 实现MD5加密
 */
public class Encrypter {
    public static String md5Encrypt(String s) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(s.getBytes("utf-8")));
    }
}
