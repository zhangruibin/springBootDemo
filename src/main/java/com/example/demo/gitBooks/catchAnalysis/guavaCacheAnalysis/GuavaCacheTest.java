package com.example.demo.gitBooks.catchAnalysis.guavaCacheAnalysis;

import com.example.entity.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
* @ClassName GuavaCacheTest
*@Description TODO
*@Author zhangrui
*@Date 14:04 14:04
*@Version 
* */
public class GuavaCacheTest {
    private static  Logger logger = LoggerFactory.getLogger(GuavaCacheTest.class);
    public static void main(String[] args) {
        Cache<Object,Object> cache = CacheBuilder
                .newBuilder()
                //设置并发级别
                .concurrencyLevel(10)
                //设置缓存有效时间,超时的话会被回收
                .expireAfterAccess(10, TimeUnit.MINUTES)
                //设置缓存条数
                .maximumSize(100)
                .build();
        final Integer userId = 10001;
        try {
            //
            User user = (User)cache.get(userId, new Callable<User>() {
                @Override
                public User call() throws Exception {
                    return getUserInfoByUserId(userId);
                }
            });
            System.out.println(user.getName() == null?"没有獲取到了用戶":"獲取到了用戶:"+user);
            logger.info("执行上述成功!!");
        } catch (ExecutionException e) {
            e.printStackTrace();
            logger.info("抛出了异常!!");
        }
    }
    public static User getUserInfoByUserId(Integer userAge) {
        User user1 = new User("张三",  10001);
        User user2 = new User("李四", 10002);
        User user3 = new User("王五", 10003);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        User user = new User();
        for (User userTemp : userList){
            if (userTemp.getAge() == userAge){
                user = userTemp;
            }
        }
        return user;
    }
}
    /*Guava Cache 支持四种回收方式，分别是
    基于容量回收（Size-based Eviction）,
    基于时间回收（Timed Eviction）,
    基于引用类型的回收Reference-based Eviction）,
    手动回收。*/
