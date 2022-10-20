package com.company.mq.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerPush {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = RabbitMqConnectionUtils.connectionFactory();
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();

            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("consumerTag: " +consumerTag);
                    System.out.println("envelope: "+envelope);
                    System.out.println("body: "+ new String(body));
                }
            };
            channel.basicConsume(Producer.QUEUE_NAME, false, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
        }



    }
}
