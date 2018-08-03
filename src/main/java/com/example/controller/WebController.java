/*
package com.example.controller;

import com.example.entity.PersonForm;
import com.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by zhangrui on 2017/11/21.
 *//*

@Controller
public class WebController extends WebMvcConfigurerAdapter{
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/results").setViewName("results");
    }
    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }
      //Valid,验证,用此注解进行定义=需要进行验证的实体类属性信息
    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        User2 user = new User2();
        user.setId(1);
        user.setName("zhangsan");
        User2 user1 = new User2();
        user.setId(2);
        user.setName("zhangsan2");
        List<User2> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        return "redirect:/results";
    }
}
*/
