package com.example.test.IO.Simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangrui on 2018/12/10.
 */
/*
* @ClassName SimpleService
*@Description TODO
*@Author zhangrui
*@Date 2018/12/10 10:14
*@Version 
*/
public class SimpleService {
    public static void main(String[] args) throws Exception {
        SimpleService.run();
    }

    private static void run() throws Exception{
        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        String msg = "";
        try {
            serverSocket = new ServerSocket(2018,10);
            System.out.println("serverSocket服务端已经建立，此时建立的时间为："+System.currentTimeMillis());
            socket = serverSocket.accept();
            System.out.println("socket链接已经建立："+socket.getInetAddress().getHostName());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //objectOutputStream.flush();
            System.out.println("开始发送消息：");
            //msg = "来看看好使不好使!";
            do {
                msg = objectInputStream.readLine();
                System.out.print(msg);
            } while (msg != null);
        }catch (IOException e){
            throw e;
        }finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (socket != null) {
                    socket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
