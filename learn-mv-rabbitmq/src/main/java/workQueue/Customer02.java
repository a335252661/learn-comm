package workQueue;

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
public class Customer02 {

    public static void main(String[] args) {
        //获取mq的连接工厂
        try {

            //获取连接中的通道
            final Channel channel = RabbitMQHelp.getChannel();

            //告诉通道每次发送一个消息给消费者
            channel.basicQos(1);

            //通道绑定对应的消息队列
            //参数1 ：队列名称，不存在则自动创建
            //参数2 ：用来定义队列特征是否需要持久化。持久化之后mq重启，队列持久化磁盘，重新后恢复，不持久化，重启队列则删除
            //参数3 ：是否独占队列，false 不独占，
            //参数4 ：是否在消费之后自动删除队列，false 不自动删除
            //参数5 ：额外参数
            channel.queueDeclare("workQueue", true, false, false, null);

            //消费队列  helloQueue
            //第二个参数：mq的自动确认机制，我们需要关闭，不关闭会一次性拿到消息，内部慢慢消费
            channel.basicConsume("workQueue", false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("Customer02 = " + new String(body));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //参数1： 当前消息的标签，就相当于消息的id，我们要告诉queue，哪条消息我们手动确认了
                    //参数2： 是否批量手动确认，我们关闭
                    channel.basicAck(envelope.getDeliveryTag(), false);

                }
            });

//            channel.close();
//            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
