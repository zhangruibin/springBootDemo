package com.utils.zxing;

import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static com.example.test.SignPerformanceTest.getQueryString;
import static javax.servlet.http.HttpUtils.parseQueryString;

/**
 * Created by zhangrui on 2018/8/3.
 */
/*
* @ClassName inCodeUrl
*@Description TODO
*@Author zhangrui
*@Date 10:28 10:28
*@Version 
* */
public class inCodeUrl {
    private static final String SIGN_KEY = "asdfsdfs2314dfsdfgffe3";
    //对 URL 进行签名返回加签后的 url
    public String signUrl(String url) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            UnsupportedEncodingException, InvalidKeySpecException {
        // 生成用于计算签名的文本，通常我们使用 url 参数名和参数值得组合
        String query = getQueryString(url);
        //String text = getTextForSign(parseQueryString(query));
        String text = "tempString";
        KeyFactory kFactory = KeyFactory.getInstance("RSA");
       byte privateKey[] = Base64.getDecoder().decode(new byte[]{'p','a','s','s','w','o','r','d'});
        //byte privateKey[] = Base64.getDecoder().decode(tempString);
        // java 中要使用 PKCS#8格式的私钥
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey publicKey = (PrivateKey) kFactory.generatePrivate(spec);
        // 计算签名
        byte[] signBytes = sign(text, publicKey);
        String signString = Base64.getEncoder().encodeToString(signBytes);
        // 吧签名拼装在URL上，作为参数传给服务端
        return url + "&" + SIGN_KEY + "=" + URLEncoder.encode(signString, "UTF-8");
    }
    /**
     * 计算摘要和签名，使用SHA256算法计算摘要，使用RSA算法和私钥加密摘要
     */
    private static byte[] sign(String textForSign, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        // 使用 SHA256 算法计算摘要
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(textForSign.getBytes("UTF-8"));
        return privateSignature.sign();
    }
    public static void test(){
        Jedis jedis = new Jedis();
        //在redis里面放入分布式的缓存锁
        String resousKey = "tempkey";
        while (true){
            jedis.setnx(resousKey,"keyVelue");
        }
          //移除锁
        //Long del = jedis.del(resousKey);
    }
}
