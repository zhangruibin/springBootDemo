package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by zhangrui on 2017/11/6.
 */
@Component
@Configuration
@ApiModel(value = "Book",description = "图书")
public class Book {
    @ApiModelProperty(value = "图书编号")
    private Long id;
    @ApiModelProperty(value = "图书名称")
    @Value("${aaaa}")
    private String name ;
    @ApiModelProperty(value = "图书价格")
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
