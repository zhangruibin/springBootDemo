package com.example.test;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.zhenzi.sms.ZhenziSmsClient;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * @ClassName sendMsg
 * @Description TODO
 * @Author Administrator
 * @Date 2019/8/1 16:41
 * @Version
 */
public class sendMsg {

    public static void main(String[] args) throws Exception {
        /*ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "102", "\n" +
                "f616627e-45b4c93");
        String result = client.send("173", "大佬");
        System.out.println(result);*/

        // 短信应用 SDK AppID
        int appid = 140013; // SDK AppID 以1400开头
        // 短信应用 SDK AppKey
        String appkey = "ec8d1d4768edcafe3";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {"153759"};
        // 短信模板 ID，需要在短信应用中申请
        int templateId = 7839; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
        // 签名
        String smsSign = "腾讯云"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[0],
                    "【腾讯云】您的验证码是: 5678", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }
}
