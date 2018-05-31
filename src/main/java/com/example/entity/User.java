package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by zhangrui on 2017/11/22.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class User {
    private String name;
    private String blog;
    private Integer id ;

    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBlog() {
        return blog;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
