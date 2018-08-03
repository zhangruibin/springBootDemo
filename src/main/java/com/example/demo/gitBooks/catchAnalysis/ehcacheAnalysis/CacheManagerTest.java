package com.example.demo.gitBooks.catchAnalysis.ehcacheAnalysis;

import com.example.entity.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;

/*
* @ClassName CacheManagerTest
*@Description TODO 声明的缓存heap大小是一定的,当后续加入缓存区满了之后,再继续加入的话则会遵从栈存储原则,堆底元素出堆消亡
*@Author zhangrui
*@Date 10:47 10:47
*@Version 
* */
public class CacheManagerTest {
    public static void main(String[] args) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        //手动初始化
        cacheManager.init();
        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.heap(2);
        //1.按照存储的条目数进行初始化
        ResourcePoolsBuilder resourcePoolsBuilder1 = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2, EntryUnit.ENTRIES);
        //2.按照存储的条目数进行初始化的简写方式
        ResourcePoolsBuilder resourcePoolsBuilder2 = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2);
        //3.按照存储空间大小初始化
        ResourcePoolsBuilder resourcePoolsBuilder3 = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(2, MemoryUnit.GB);
        CacheConfiguration<Integer, User> configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, User.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(10, MemoryUnit.KB)
                        .offheap(10, MemoryUnit.MB))
                .withSizeOfMaxObjectGraph(3)
                .withSizeOfMaxObjectSize(4, MemoryUnit.KB)
                .build();
        //创建缓存对象
        Cache<Integer, User> cache = cacheManager.createCache("userInfo", CacheConfigurationBuilder.newCacheConfigurationBuilder(configuration));
        //写入缓存
        cache.put(10001, new User("张三",  1));
        cache.put(10002, new User("李四",  2));
        cache.put(10003, new User("王五",3));
        //读取缓存
        User user1 = new User();
        user1 = cache.get(10001);
        User user2 = new User();
        user2 = cache.get(10002);
        User user3 = new User();
        user3 = cache.get(10003);
        //输出缓存
        System.out.println(user1 == null ? "获取失败,以为没有成功放入缓存,其实是堆溢出清除!!" : "成功的获取了缓存:" + "**************" + user1 + "***********");
        System.out.println("成功的获取了缓存:" + "**************" + user2 + "***********");
        System.out.println(user3 == null ? "获取失败,以为没有成功放入缓存,可能的原因为声明的heap值过小!!" : "成功的获取了缓存:" + "**************" + user3 + "***********");
        //再程序关闭前，需要手动释放资源
        //不用手动removeCache,根据打印日志,会自动清除;
        cacheManager.removeCache("userInfo");
        cacheManager.close();
    }
}
