package com.example.test.IO.NIO;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.ReentrantLock;

/*
* @ClassName NioServer
*@Description TODO
*@Author zhangrui
*@Date 2018/12/10 11:00
*@Version
*/
public class NioServer extends Thread{
    //private static  Logger logger = LoggerFactory.getLogger(NioServer.class);
    public static void main(String[] args) {
        NioServer nioServer = new NioServer("localhost", 2018);
        nioServer.start();

        ReentrantLock lock = new ReentrantLock();
        lock.unlock();
    }
    private InetSocketAddress address;
    private final Handler handler = new ServerHandler();

    public NioServer(String hostname, int port) {
        System.out.println("111111111111111111111111111");
        address = new InetSocketAddress(hostname, port);
    }
    protected interface Handler {
        void handleAccept(SelectionKey key) throws IOException;

        void handleRead(SelectionKey key) throws IOException;

        void handleWrite(SelectionKey key) throws IOException;
    }
    private class ServerHandler implements Handler {
        @Override
        public void handleAccept(SelectionKey key) throws IOException {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            //logger.info("Server: accept client socket " + socketChannel);
            System.out.println("Server: accept client socket " + socketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        }

        @Override
        public void handleRead(SelectionKey key) throws IOException {
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            SocketChannel socketChannel = (SocketChannel) key
                    .channel();
            while (true) {
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    //logger.info("Server: readBytes = " + readBytes);
                    System.out.println("Server: readBytes = " + readBytes);
                    //logger.info("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
                    System.out.println("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    break;
                }
            }
            socketChannel.close();
        }

        @Override
        public void handleWrite(SelectionKey key) throws IOException {
            ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
            byteBuffer.flip();
            SocketChannel socketChannel = (SocketChannel) key.channel();
            socketChannel.write(byteBuffer);
            if (byteBuffer.hasRemaining()) {
                key.interestOps(SelectionKey.OP_READ);
            }
            byteBuffer.compact();
        }
    }
}
