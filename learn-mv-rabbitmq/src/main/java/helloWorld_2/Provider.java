package helloWorld_2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import help.RabbitMQHelp;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/16
 */
public class Provider {


    /**
     * 第一种模式：直连模式，不经过交换机
     */
    @Test
    public void fun() {
        try {
            Channel channel = RabbitMQHelp.getChannel();
            //通道绑定对应的消息队列
            //参数1 ：队列名称，不存在则自动创建
            //参数2 ：用来定义队列特征是否需要持久化。持久化之后mq重启，队列持久化磁盘，重新后恢复，不持久化，重启队列则删除
            //参数3 ：是否独占队列，false 不独占，
            //参数4 ：是否在消费之后自动删除队列，false 不自动删除    ，消息消费完，并且没有消费者在监听才会删除
            //参数5 ：额外参数
            channel.queueDeclare("helloQueue", false, false, false, null);
            //参数1 ： 交换机名称
            //参数2 ： 队列名称  helloQueue  交换机通过routingkey 将消息发送到对应的queue上去
            //参数3 ： 消息的额外设置
            //参数4 ： 消息名称
            channel.basicPublish("", "helloQueue", null, "hello rabitmq!!!!!!".getBytes());




            //第二个参数： true，队列的持久化
//            channel.queueDeclare("helloQueue" ,true,false,false,null);
            //消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
//            channel.basicPublish("","helloQueue", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabitmq!!!!!!".getBytes());

            RabbitMQHelp.closeMQ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
