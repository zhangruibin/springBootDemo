package com.example.service.impl;

import com.example.mapper.AccountMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangrui on 2017/10/20.
 */
@Service
public class AccountService2 {
    @Autowired
    AccountMapper2 accountMapper2;

    @Transactional
    public void transfer() throws RuntimeException{
       int i =  accountMapper2.update(6666,1);//用户1减10块 用户2加10块
        //int i=1/0;
        int j = accountMapper2.update(8888,2);
        if (i==j){

        }else {
            //事物,如果两个么成功执行则运行此处,报错,事务回滚
            int error = 1/0;
        }
    }
}
