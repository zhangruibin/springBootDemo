/*
package com.example.demo;

import com.example.entity.ConfigBean;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * Created by zhangrui on 2017/10/18.
 *//*

@RestController
@EnableConfigurationProperties({ConfigBean.class, User.class})
public class LucyController {
    */
/**
     *
     *//*

    @Autowired
    private ConfigBean configBean;
    @RequestMapping(value = "/lucy")
    public String miya(){
        return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
    }

    */
/**
     *
     *//*

    @Autowired
    User user;
    @RequestMapping(value = "/user")
    public String user(){
        return user.getName()+user.getAge();
    }
}
*/
