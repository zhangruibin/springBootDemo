package com.example.test.IO.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TimeClient
 * @Description TODO 模拟BIO的特性
 * @Author Administrator
 * @Date 2019/7/15 15:46
 * @Version
 */
public class TimeClient {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args)  {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client=null;
        try {
            client=new Socket("127.0.0.1",8080);
            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while (true){
                if (count.getAndIncrement()% 3 == 0 ){
                    writer.println("GET CURRENT TIME");
                }else {
                    writer.println("BAD REQUEST");
                }
                writer.flush();
                String response = reader.readLine();
                System.out.println(response);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
