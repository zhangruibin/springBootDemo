package com.example.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Service;

/**
 * Created by zhangrui on 2017/11/21.
 */
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfiguration {
    @Bean
    public  Service service(ServiceProperties properties) {
      /*  return new Service(properties.getMessage());*/
      return null;
    }
}
