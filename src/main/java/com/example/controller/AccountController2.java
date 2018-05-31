package com.example.controller;

import com.example.service.impl.AccountService2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangrui on 2017/10/20.
 */
@RestController
@RequestMapping("/account2")
@MapperScan("com.example.mapper")
public class AccountController2 {
    @Autowired
    AccountService2 accountService2;
    @RequestMapping(value = "transfer", method = RequestMethod.GET)
    public void transfer(){
        accountService2.transfer();
    }
}
