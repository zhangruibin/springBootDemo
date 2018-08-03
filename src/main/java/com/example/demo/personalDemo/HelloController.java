package com.example.demo.personalDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangrui on 2017/10/17.
 */
/*
@RestController
public class HelloController {

    @Value("${my.name}")
    private  String name ;
    @Value("${my.age}")
    private  int age;
    @RequestMapping(value = {"/hello","/hi"}, method = RequestMethod.GET)
    public String say(){
        String sysOut = "This is just for test new demo...." +"name:"+ name+"age:"+age;
        return sysOut ;
    }
    @Bean
    public CommandLineRunner commandLineRunner (ApplicationContext ctx){
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            for (String a:beanDefinitionNames){
                System.out.println(a);
            }
        };
    }
}
*/
