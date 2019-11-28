package com.xxt.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recevier {

    private static final String QUEUE = "simpleQueue";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("49.235.190.5");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);
            DeliverCallback deliverCallback = (comsumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("接收到消息：" + message);
            };
            channel.basicConsume(QUEUE, true, deliverCallback, cosumerTag -> {
            });

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
