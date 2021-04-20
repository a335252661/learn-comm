package direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import help.RabbitMQHelp;

import java.io.IOException;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/16
 */
public class ConsumeErr {
    public static void main(String[] args) throws IOException {

        Channel channel = RabbitMQHelp.getChannel();
        channel.exchangeDeclare("logs_direct_exchange", "direct");
        //获取临时队列
        final String queueName = channel.queueDeclare().getQueue();
        //绑定临时队列和交换机
        channel.queueBind(queueName, "logs_direct_exchange", "err");
        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            //输入 o  可以快速重写接口的声明    方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("err -消费者1：临时队列--" + queueName + new String(body));
            }
        });


    }
}
