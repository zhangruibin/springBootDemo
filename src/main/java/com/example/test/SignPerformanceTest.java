package com.example.test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;

/**
 * Created by zhangrui on 2018/8/1.
 */
/*
* @ClassName SignPerformanceTest
*@Description TODO
*@Author zhangrui
*@Date 16:40 16:40
*@Version 
* */
public class SignPerformanceTest {
    public static final int LOOP = 100;
    // 创建秘钥
    public static KeyPair createKey(String algorithm, int bit) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(bit);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
    // 验证签名
    public static boolean verify(String plainText, byte[] signature, PublicKey publicKey, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        Signature publicSignature = Signature.getInstance(algorithm);
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes("UTF-8"));
        return publicSignature.verify(signature);
    }
    // 生产签名
    public static byte[] sign(String textForSign, PrivateKey privateKey, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        Signature privateSignature = Signature.getInstance(algorithm);
        privateSignature.initSign(privateKey);
        privateSignature.update(textForSign.getBytes("UTF-8"));
        return privateSignature.sign();
    }
    // 签名性能测试
    public static void testSign(String textForSign, PrivateKey privateKey, String algorithm, int bit)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
            UnsupportedEncodingException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            sign(textForSign, privateKey, algorithm);
        }
        long end = System.currentTimeMillis();
        System.out.println(algorithm + "签名耗时（" + bit + "位秘钥）：" + (end - start)
                / ((double) LOOP) + " ms");
    }
    // 验证签名性能测试
    public static void testVerify(String plainText, byte[] signature, PublicKey publicKey, String algorithm, int bit)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
            UnsupportedEncodingException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < LOOP; i++) {
            boolean r = verify(plainText, signature, publicKey, algorithm);
        }
        long end = System.currentTimeMillis();
        System.out.println(algorithm + "验签耗时（" + bit + "位秘钥）：" + (end - start)
                / ((double) LOOP) + " ms");
    }
    public static String getQueryString(String url) {
        try {
            URL urlObj = new URL(url);
            return urlObj.getQuery();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        String[][] a = { { "DSA", "SHA1withDSA", "1024" }, { "DSA", "SHA256withDSA", "1024" },
                { "DSA", "SHA256withDSA", "2048" }, { "RSA", "SHA256withRSA", "1024" }
                ,
                { "RSA", "SHA256withRSA", "2048" }, { "RSA", "SHA256withRSA", "3192" }
                ,
                { "RSA", "SHA512withRSA", "1024" }, { "RSA", "SHA512withRSA", "2048" }
                ,
                { "RSA", "SHA512withRSA", "3192" }, { "RSA", "MD5withRSA", "1024" }, {
                "RSA", "MD5withRSA", "2048" },
                { "RSA", "MD5withRSA", "3192" }, { "EC", "SHA1withECDSA", "128" }, { "EC", "SHA1withECDSA", "256" },
                { "EC", "SHA256withECDSA", "128" }, { "EC", "SHA256withECDSA", "256" }
                ,
                { "EC", "SHA512withECDSA", "128" }, { "EC", "SHA512withECDSA", "256" }
                ,
        };
        String text = "user_id=1234&type=2&price=60";
        for (String[] eachAlg : a) {
            int bit = Integer.parseInt(eachAlg[2]);
            KeyPair keys = createKey(eachAlg[0], Integer.parseInt(eachAlg[2]));
            testSign(text, keys.getPrivate(), eachAlg[1], bit);
            byte[] sign = sign(text, keys.getPrivate(), eachAlg[1]);
            testVerify(text, sign, keys.getPublic(), eachAlg[1], bit);
            System.out.println("--------------------------------");
        }
        System.out.println(Security.getAlgorithms("signature"));
    }
}
