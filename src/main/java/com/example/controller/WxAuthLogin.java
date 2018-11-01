package com.example.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.extras.springsecurity4.auth.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangrui on 2018/9/18.
 */
/*
* @ClassName WxAuthLogin
*@Description TODO
*@Author zhangrui
*@Date 9:16 9:16
*@Version 
* */
@Api(value = "微信登录接口")
@Controller
@RequestMapping("/wxAuthLogin")
public class WxAuthLogin {
    private String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID" +
            "&redirect_uri=REDIRECT_URI" +
            "&response_type=code" +
            "&scope=SCOPE" +
            "&state=STATE#wechat";
    @RequestMapping("/test")
    public String wxAuthLogin(String url){
        url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        System.out.println("进入到方法中");
        URLConnection connection;
        OutputStream outputStream ;
        URL authUrl ;
        String result = "";
        String line = "";
        StringBuffer resultSting = new StringBuffer();
        try {
            authUrl = new URL(url);
            connection = authUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            outputStream = connection.getOutputStream();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            System.out.println("**********返回的报文为:"+outputStream.toString());
            for (line = br.readLine(); line != null; line = br.readLine()) {
                //对返回的报文进行结果判断<RspCode>0000</RspCode>
                System.out.println(">>>>>>>>>>>>>>>>>>>返回的结果报文内容为:---------"+line);
                //对返回的报文进行拼接,然后返回给业务层,在业务层进行判断
                result += line;
            }
            outputStream.flush();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("进入到了finally中");
        }
        System.out.println(result);
        return result;
    }

 /*   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到get方法中");
        super.doGet(req, resp);
    }*/
}
