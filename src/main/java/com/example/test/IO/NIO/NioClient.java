package com.example.test.IO.NIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangrui on 2018/12/10.
 */
/*
* @ClassName NioClient
*@Description TODO
*@Author zhangrui
*@Date 2018/12/10 11:13
*@Version 
*/
public class NioClient {

    private InetSocketAddress inetSocketAddress;
    private static Logger logger = LoggerFactory.getLogger(NioClient.class);

    public NioClient(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    public void send(String data) {
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            socketChannel.write(ByteBuffer.wrap(data.getBytes()));
            while (true) {
                byteBuffer.clear();
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    logger.info("Client: readBytes = " + readBytes);
                    logger.info("Client: data = " + byteBuffer.toString());
                    socketChannel.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NioClient("localhost", 2018).send("Hi, this is dolly speaking!");
    }
}
