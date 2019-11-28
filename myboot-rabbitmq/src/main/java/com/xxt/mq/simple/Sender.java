package com.xxt.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    private static final String QUEUE="simpleQueue";

    public static void main(String[] args) {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("49.235.190.5");
        Connection connection=null;
        Channel channel=null;
        try {
            connection= factory.newConnection();
            channel= connection.createChannel();
            channel.queueDeclare(QUEUE,false,false,false,null);
            String msg="a simple message";
            channel.basicPublish("",QUEUE,null,msg.getBytes());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

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
