package com.example.demo.designPattern.monitorDesignPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Created by zhangrui on 2019/1/2.
 */
/*
* @ClassName MainTest
*@Description TODO
*@Author zhangrui
*@Date 2019/1/2 14:41
*@Version 
*/
public class MainTest {
    public static void main(String[] args) throws FileNotFoundException {
        BeingWatcher beingWatcher = new BeingWatcher();
        Watcher watcher = new Watcher();
        beingWatcher.addObserver(watcher);
        beingWatcher.counter(5);
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(""),"rw");
    }
}
