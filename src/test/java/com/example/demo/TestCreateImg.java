package com.example.demo;

import com.utils.zxing.QRCodeUtil;
import com.utils.zxing.QrCodeCreateUtil;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhangrui on 2018/7/27.
 */
/*
* @ClassName TestCreateImg
*@Description TODO
*@Author zhangrui
*@Date 16:14 16:14
*@Version 
* */
public class TestCreateImg {
    public static void main(String[] args) throws Exception {

        //生成带logo 的二维码
        String text = "这就是在测试????";
        QRCodeUtil.encode(text, "zxingTempImg/苏勇.jpg"   , "d:/tempImg", true);

        /*//生成不带logo 的二维码
        String textt = "http://www.baidu.com";
        QRCodeUtil.encode(textt, "", "d:/tempImg", true);*/

        //指定二维码图片，解析返回数据
       /* System.out.println(QRCodeUtil.decode("D:/tempImg/aaa.jpg"));*/
       /* OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };
        QrCodeCreateUtil.createQrCode(outputStream,"shishi",50000,"test");*/

    }
}