/*
package com.example.demo;

import com.example.entity.Account;

import com.example.service.AccountService;
import com.example.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

*/
/**
 * Created by zhangrui on 2017/10/18.
 *//*

@RestController
@RequestMapping("/account")
public class AccountController {

    */
/*@Autowired(required = false)
    IAccountService accountService;*//*

    @Autowired(required = false)
    AccountService accountService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Account> getAccounts(){
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/id",method = {RequestMethod.GET,RequestMethod.POST})
    public  Account getAccountById(){
       int id = 1;
        Account accountById = accountService.findAccount(id);
        return accountById;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  Account getAccountById2(@PathVariable("id") int id){
        return accountService.findAccount(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
                                 @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(name,money,id);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public  String postAccount( @RequestParam(value = "name")String name,
                                @RequestParam(value = "money" )double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.add(name,money);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }

}
*/
