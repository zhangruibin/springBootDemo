package com.example.demo.personalDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by zhangrui on 2017/12/26.
 */
public class Producer {
    public final static String QUEUE_NAME="rabbitMQ.test";
    public static void main(String[] args) throws Exception{
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(15672);
        //创建一个新的链接
        Connection connection = connectionFactory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明对列
        //queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
        // 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
        // 第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息到队列中
        //basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
        channel.basicPublish("",QUEUE_NAME,null,"helloHERO".getBytes());
        System.out.println("Producter send msg successfully");
        //先关闭通道再关闭连接
        channel.close();
        connection.close();

    }
}
