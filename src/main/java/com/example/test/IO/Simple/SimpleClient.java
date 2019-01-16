package com.example.test.IO.Simple;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by zhangrui on 2018/12/10.
 */
/*
* @ClassName SimpleClient
*@Description TODO
*@Author zhangrui
*@Date 2018/12/10 10:33
*@Version 
*/
public class SimpleClient {
    public static void main(String[] args) throws Exception {
        SimpleClient.run();
    }

    private static void run() throws Exception{
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            socket = new Socket("localhost", 2018);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println((String)in.readObject());
            System.out.println("建立鏈接locahost:2018");
            out.writeObject("Hello World!");
            out.flush();
            System.out.println((String)in.readObject());
            out.writeObject("over！");
            out.flush();
            System.out.println((String)in.readObject());
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
