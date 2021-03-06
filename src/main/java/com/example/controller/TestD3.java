package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Book;
/*import com.example.entity.OrderStatusEnum;
import com.example.entity.test1.ActivityInfo;
import com.example.entity.test1.ActivityInterfaceBiz;
import com.example.entity.test1.ActivityInterfaceHead;
import com.example.entity.test1.ActivityRequestResult;*/
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangrui on 2018/8/20.
 */
/*
* @ClassName TestD3
*@Description TODO
*@Author zhangrui
*@Date 11:11 11:11
*@Version 
* */
@Api(value = "测试d3构建可视化数据")
@RequestMapping(value = "/d3")
@Controller
@Slf4j
public class TestD3 {
    private static Logger logger = LoggerFactory.getLogger(TestD3.class);
    @Value("${bbbb}")
    private String aaaa ;
    @RequestMapping("/first")
    public String firstD3Html(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "view/index";
    }
    //@RequestMapping("/second")
    /*public String secondD3Html(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        //log.info("这是使用@Slf4j注解进行异步打印的日志！");
        String jsonString = "{\n" +
                "\t\"head\": {\n" +
                "\t\t\"sysCode\": \"S10300\",\n" +
                "\t\t\"appCode\": \"A01013\",\n" +
                "\t\t\"transactionId\": \"S10300A0101318092022351d4f4353d15648d9863fdb89492cd101\",\n" +
                "\t\t\"reqTime\": \"2018-09-20 22:35:25\",\n" +
                "\t\t\"method\": \"PMS01002\",\n" +
                "\t\t\"version\": \"1\",\n" +
                "\t\t\"attach\": \"hello,189.cn\",\n" +
                "\t\t\"sign\": \"e957b9fdec47474c8e5bf7e9e083027a\"\n" +
                "\t},\n" +
                "\t\"biz\": {\n" +
                "\t\t\"orderStatus\": {\n" +
                "\t\t\t\"name\": \"HAVE_SHIPPED\",\n" +
                "\t\t\t\"code\": \"10111\",\n" +
                "\t\t\t\"desc\": \"已发货\"\n" +
                "\t\t},\n" +
                "\t\t\"orderId\": \"690000000001008318091918369097\",\n" +
                "\t\t\"operateType\": \"2\",\n" +
                "\t\t\"operateTime\": \"2018-09-20 22:35:25\",\n" +
                "\t\t\"expressId\": \"249430931881\",\n" +
                "\t\t\"expressCompany\": \"shunfeng\",\n" +
                "\t\t\"expressName\": \"??\",\n" +
                "\t\t\"expressWEB\": \"www.sf-express.com/cn/sc\",\n" +
                "\t\t\"reason\": \"\",\n" +
                "\t\t\"iccid\": null,\n" +
                "\t\t\"pickupPoint\": null,\n" +
                "\t\t\"operator\": null,\n" +
                "\t\t\"sender\": null,\n" +
                "\t\t\"senderMoblie\": null,\n" +
                "\t\t\"remark\": null\n" +
                "\t}\n" +
                "}";
        Map<String, Object> map1 = addExtraParameters(jsonString);
        String s = OrderStatusEnum.ACTIVE_BUSINESS.toString();
        //测试从文件之中进行数据的读取
        testValue(aaaa);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(OrderStatusEnum.PRE_CHECKOUT_FAILURE.toString());
        //String s1 = orderStatusEnum.toString("00000");
        //System.out.println("s1_______________________"+s1);
        return "view/index2";
    }*/
    /*@ResponseBody
    @RequestMapping(value = "/second", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject createActivity(@RequestBody ActivityRequestResult<ActivityInterfaceHead,ActivityInterfaceBiz> activityRequestResult) throws Throwable {
        ActivityInfo activityInfo = new ActivityInfo();
        JSONObject jsonResult = new JSONObject();
        JSONObject head = new JSONObject();
        JSONObject biz = new JSONObject();
        String partResult;
        head.put("transactionId","S000011410152301988989769ac044a6a52885475707c30d");
        head.put("resTime","");
        head.put("code","");
        head.put("err",null);
        head.put("attach","hello, 189.cn");
        //返回结果集合
        //设置返回值
        List<ActivityInfo> activityInfoList;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (!"4".equals(activityRequestResult.getBiz().getVerifyStatus()) ){
                head.put("err","非审核通过状态！");
                head.put("code","1");
            }else {
                activityInfo .setActivityCode(activityRequestResult.getBiz().getActivityId());
                activityInfo.setActivityBeginDate(sdf.parse(activityRequestResult.getBiz().getActivityBeginDate()));
                activityInfo.setActivityEndDate(sdf.parse(activityRequestResult.getBiz().getActivityEndDate()));
                activityInfo.setActivityName(activityRequestResult.getBiz().getActivityName());
                activityInfo.setPartnerId(activityRequestResult.getBiz().getPartnerId());
                activityInfo.setVerifyStatus(activityRequestResult.getBiz().getVerifyStatus());
                activityInfo.setPartnerName(activityRequestResult.getBiz().getPartnerName());
                //activityInfoList = activityService.selcetActivityInfoList1(activityInfo);
            *//*    if (activityInfoList.size() > 0){
                    if (activityInfoList.get(0).getStatus().equals(activityRequestResult.getBiz().getReleaseStatus())){
                        head.put("err","已有此活动信息，且信息一致，请重新核对数据！");
                        head.put("code","1");
                    }else {
                        activityInfo.setStatus(activityRequestResult.getBiz().getReleaseStatus());
                        activityInfo.setId(activityInfoList.get(0).getId());
                        //activityService.updateActivityInfo(activityInfo);
                        partResult = "1".equals(activityRequestResult.getBiz().getReleaseStatus())?"上架！":"下架！";
                        head.put("err","成功将活动状态更新为" + partResult);
                        head.put("code","0");
                    }
                }else {
                    //activityService.insertActivity(activityInfo);
                    head.put("err","新建拉新活动信息成功！");
                    head.put("code","0");
                }*//*
            }
        }catch (Exception e){
            head.put("err","接口异常，请联系管理员！");
            head.put("code","1");
            e.printStackTrace();
        }finally {
            jsonResult.put("head",head);
            jsonResult.put("biz",null);
            return jsonResult;
        }
    }*/
    @Value("${pcacmgr.publicCertFilePath}")
    public static void testValue(String aa){
        Book book = new Book();
        String a = book.getName();
        System.out.println(aa);
    }
    public static Map<String, Object> addExtraParameters(String jsonString) {
        //第二种方式
        JSONObject obj = JSONObject.parseObject(jsonString);
        Stack<JSONObject> stObj = new Stack<JSONObject>();
        stObj.push(obj);
        Map<String, Object> resultMap =new HashMap<String, Object>();
        Map map = new HashedMap();
        try {
            map = JsonToMap(stObj, resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> finallyResultMap = new HashedMap();
        Map parametersMap = new LinkedHashMap();
        String sysCode = "";
        String transactionId = "";
        String number = "";
        String offerTitle = "";
        String abType = "";
        String orderStatus = "";
        String iccid = "";
        String operateType = "";
        String expressId = "";
        String expressName = "";
        String reason = "";
        try {
            sysCode = map.containsKey("sysCode") ? map.get("sysCode").toString() : "";
            transactionId = map.containsKey("transactionId") ? map.get("transactionId").toString() : "";
            number = map.containsKey("number") ? map.get("number").toString() : "";
            offerTitle = map.containsKey("offerTitle") ? map.get("offerTitle").toString() : "";
            abType = map.containsKey("abType") ? map.get("abType").toString() : "";
            orderStatus = map.containsKey("orderStatus") ? map.get("orderStatus").toString() : "";
            iccid = map.containsKey("iccid") ? map.get("iccid").toString() : "";
            operateType = map.containsKey("operateType") ? map.get("operateType").toString() : "";
            expressId = map.containsKey("expressId") ? map.get("expressId").toString() : "";
            expressName = map.containsKey("expressName") ? map.get("expressName").toString() : "";
            reason = map.containsKey("reason") ? map.get("reason").toString() : "";
        } catch (Exception e) {
            System.out.println("工具类AddParametersUtil中addExtraParameters抛出异常，异常信息为：");
            e.printStackTrace();
        } finally {
            finallyResultMap.put("sysCode", sysCode);
            finallyResultMap.put("transactionId", transactionId);
            finallyResultMap.put("number", number);
            finallyResultMap.put("offerTitle", offerTitle);
            finallyResultMap.put("abType", abType);
            finallyResultMap.put("orderStatus", orderStatus);
            finallyResultMap.put("iccid", iccid);
            finallyResultMap.put("operateType", operateType);
            finallyResultMap.put("expressId", expressId);
            finallyResultMap.put("expressName", expressName);
            finallyResultMap.put("reason", reason);
        }
        return finallyResultMap;
    }
    private static Map JsonToMap(Stack<JSONObject> stObj, Map<String, Object> resultMap)  throws Exception{

        if(stObj == null && stObj.pop() == null){
            return null;
        }
        JSONObject json = stObj.pop();
        Set<String> strings = json.keySet();
        Iterator it = strings.iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            //得到value的值
            Object value = json.get(key);
            //System.out.println(value);
            if (!key.equals("orderStatus")){
                if(value instanceof JSONObject)
                {
                    stObj.push((JSONObject)value);
                    //递归遍历
                    JsonToMap(stObj,resultMap);
                }else {
                    resultMap.put(key, value);
                }
            }else {
                resultMap.put(key, value);
            }
        }
        return resultMap;
    }
}
