package com.example.test;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by zhangrui on 2018/8/1.
 */
/*
* @ClassName CipherAES_CBC
*@Description TODO 64位加密解密
*@Author zhangrui
*@Date 16:22 16:22
*@Version 
* */
public class CipherAES_CBC {
    public static Key genKey(String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        return secretKey;
    }
    // 生产随机初始向量
    public static IvParameterSpec genRandomIV() throws Exception {
        byte[] randomBytes = new byte[16];
        SecureRandom r = new SecureRandom();
        r.nextBytes(randomBytes);
        IvParameterSpec iv = new IvParameterSpec(randomBytes);
        return iv;
    }
    // 加密方法
    public static byte[] encrypt(Key key, String text, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(text.getBytes());
    }
    // 解密方法
    public static byte[] decrypt(Key key, byte[] data, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(data);
    }
    public static void main(String[] args) throws Exception {
        // 明文
        String text = "行用卡账号：4567 8910 1112 1314";
        // 128位16字节的秘钥，AES 只支持 128 192 和 256 位的三种秘钥，否则会报错
        String keyStr = "r3pNS{>Zpk-t=h54";
        Key key = genKey(keyStr);
        byte[] randomBytes = new byte[16];
        SecureRandom r = new SecureRandom();
        r.nextBytes(randomBytes);
        IvParameterSpec iv = new IvParameterSpec(randomBytes);
        // 加密
        byte[] encrypted = encrypt(key, text, iv);
        // 解密
        byte[] decrypted = decrypt(key, encrypted, iv);
        System.out.println("秘钥 : " + keyStr);
        System.out.println("初始向量（base64编码）: " + Base64.encodeBase64String(iv.getIV()));
        System.out.println("加密后密文（base64编码）: " + Base64.encodeBase64String(encrypted));
        System.out.println("解密后明文:" + new String(decrypted));
    }
}
