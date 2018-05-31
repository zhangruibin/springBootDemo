package com.example.controller;

/**
 * Created by zhangrui on 2017/11/21.
 */
public class StorageException extends RuntimeException{
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
