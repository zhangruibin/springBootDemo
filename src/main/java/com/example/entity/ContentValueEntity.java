package com.example.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/*
* @ClassName ContentValueEntity
*@Description TODO
*@Author zhangrui
*@Date 2018/11/20 15:03
*@Version 
*/

public class ContentValueEntity {
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date nowTime;

    private int secondValue;

    private String thirdValue;

    private boolean fourthValue;

    private int fivthValue;

    private boolean sixthValue;

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public String getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(String thirdValue) {
        this.thirdValue = thirdValue;
    }

    public boolean isFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(boolean fourthValue) {
        this.fourthValue = fourthValue;
    }

    public int getFivthValue() {
        return fivthValue;
    }

    public void setFivthValue(int fivthValue) {
        this.fivthValue = fivthValue;
    }

    public boolean isSixthValue() {
        return sixthValue;
    }

    public void setSixthValue(boolean sixthValue) {
        this.sixthValue = sixthValue;
    }

    public ContentValueEntity() {
    }

    public ContentValueEntity(Date nowTime, int secondValue, String thirdValue, boolean fourthValue, int fivthValue, boolean sixthValue) {
        this.nowTime = nowTime;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
        this.fivthValue = fivthValue;
        this.sixthValue = sixthValue;
    }
}
