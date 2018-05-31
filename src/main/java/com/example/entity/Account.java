package com.example.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by zhangrui on 2017/10/18.
 */
/*@EntityScan*/
public class Account {
    private int id ;
    private String name ;
    private double money;

    public void setMoney(double money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public int getId() {
        return id;
    }
}
