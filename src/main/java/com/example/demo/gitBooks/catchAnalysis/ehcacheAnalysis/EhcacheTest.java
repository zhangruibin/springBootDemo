package com.example.demo.gitBooks.catchAnalysis.ehcacheAnalysis;

import com.example.entity.User;
import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.CacheManagerConfiguration;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.io.File;
import java.util.concurrent.TimeUnit;

/*
* @ClassName EhcacheTest
*@Description TODO 同时初始化堆缓存,堆外缓存,磁盘缓存,
* TODO 其中:heap + offheap 的组合，offheap 就是 Authoritative Tier，heap，就是 Caching Tier；
*TODO 对于heap + offheap + disk 的组合，disk 层就是 Authoritative Tier，heap + offheap 就是 CachingTier。
*@Author zhangrui
*@Date 10:47 10:47
*@Version 
* */
public class EhcacheTest {
    public static void main(String[] args) {
        //声明持久化(persistence)的磁盘位置为g盘
        //TODO 在Linux下需要修改
        CacheManagerConfiguration<PersistentCacheManager> persistentManagerConfig = CacheManagerBuilder
                .persistence(new File("g:\\", "ehcache-test"));
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(persistentManagerConfig).build();
        // 手动初始化
        persistentCacheManager.init();
        // 空间大小 heap < offheap < disk，否则会报错java.lang.IllegalArgumentException
        //第三个参数设置为true，支持持久化
        ResourcePoolsBuilder resource = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10, MemoryUnit.MB)
                .offheap(100, MemoryUnit.MB)
                .disk(500, MemoryUnit.MB, true);
        //写入缓存
        CacheConfiguration<Long, User> config = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, User.class, resource)
                //设置Caching Tier缓存有效时间,因为存入缓存是直接存到比 AuthoritativeTier中,所以,Caching Tier失效也不会影响数据
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(10, TimeUnit.SECONDS)))
                .build();
        Cache<Long, User> cache = persistentCacheManager.createCache("userInfoCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(config));
        //写入缓存
        cache.put(10001L, new User("张三",  1));
        cache.put(10002L, new User("李四",  2));
        cache.put(10003L, new User("王五",  3));
        //读取缓存,如果要读取的是未存放放的,不会抛出异常,返回为null
        User user1 = new User();
        user1 = cache.get(10001L);
        User user2 = new User();
        user2 = cache.get(10002L);
        User user3 = new User();
        user3 = cache.get(10003L);
        //输出缓存
        System.out.println(user1 == null ? "获取失败,以为没有成功放入缓存,其实是堆溢出清除!!" : "成功的获取了缓存:" + "**************" + user1 + "***********");
        System.out.println("成功的获取了缓存:" + "**************" + user2 + "***********");
        System.out.println(user3 == null ? "获取失败,以为没有成功放入缓存,可能的原因为声明的heap值过小!!" : "成功的获取了缓存:" + "**************" + user3 + "***********");
        // 再程序关闭前，需要手动释放资源
        persistentCacheManager.close();
    }
}
   /* Expirations 有三种方式：
        永不失效：Expirations.noExpiration();
        TTL（time-to-live）：自缓存项创建之后多久失效，例如：
        Expirations.timeToLiveExpiration(Duration.of(10,TimeUnit.MINUTES));
        TTI（time-to-idle）：缓存项自上次方位之后空闲（没有被再次访问）多久后失效，例
        如：Expirations.timeToIdleExpiration(Duration.of(5, TimeUnit.MINUTES));
        另外，Ehcache 中还可以通过自行实现Expiry接口来自定义缓存失效策略。
*/