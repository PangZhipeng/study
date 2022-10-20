package rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang.time.DateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static final String QUEUE_NAME = "hello_world";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = RabbitMqConnectionUtils.connectionFactory();
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            // 声明一个quorum队列
            Map<String,Object> quorumMap = new HashMap<>();
            quorumMap.put("x-queue-type","quorum");
            // 声明一个stream队列
            Map<String,Object> streamMap = new HashMap<>();
            streamMap.put("x-queue-type","stream");
            streamMap.put("x-max-length-bytes", 20_000_000_000L); // maximum stream
            streamMap.put("x-stream-max-segment-size-bytes", 100_000_000);

            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            String message = "my first rabbit mq quorum message" + new Date();
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("mq 消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
