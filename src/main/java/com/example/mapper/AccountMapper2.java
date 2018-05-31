package com.example.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangrui on 2017/10/20.
 */
//@Mapper
public interface AccountMapper2 {
    int update(@Param("money") double money, @Param("id") int id);

}
