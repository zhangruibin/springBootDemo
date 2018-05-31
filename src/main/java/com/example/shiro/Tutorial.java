package com.example.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangrui on 2018/2/2.
 */
public class Tutorial {
    private static final transient Logger logger = LoggerFactory.getLogger(Tutorial.class);
    /**
    * @author zhangrui
    * @Description this class is just for test shiro
    * @Date 14:18 2018/2/5
    * @Param
    * @returu
    **/
    public static void main(String[] args) {
        logger.info("This is my first Apache Shiro Application");
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("key","value");
        if (!currentUser.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("hehe", "hehe");
            usernamePasswordToken.setRememberMe(true);
            logger.info("User ["+currentUser.getPrincipal() + "] loggered in successfully");
            if (currentUser.hasRole("client")){
                logger.info("LOok is in your role");
            }else {
                logger.info("this is just for test");
            }
            // 查看用户是否有某个权限
            if ( currentUser.isPermitted( "look:desk" ) ) {
                logger.info("You can look.  Use it wisely.");
            } else {
                logger.info("Sorry, you can't look.");
            }

            if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
                logger.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                        "Here are the keys - have fun!");
            } else {
                logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
            }

            //登出

            currentUser.logout();
        }
        System.exit(0);
        test(new int[]{1});
    }
    /**
    * @author zhangrui
    * @Description 
    * @Date 17:37 2018/2/5
    * @Param 
    * @returu 
    **/
    public static void test(int a){
        System.out.println("A");
    }
    public static void test(int... a){
        System.out.println("B");
    }
}
