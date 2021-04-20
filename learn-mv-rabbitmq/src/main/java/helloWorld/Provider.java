package helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
        //获取mq的连接工厂
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            // http://192.168.106.128:15672/#/users
            connectionFactory.setHost("192.168.106.128");
            connectionFactory.setPort(5672);//通讯端口
            connectionFactory.setVirtualHost("/ems");//设置虚拟主机
            //配置虚拟主机能够访问的用户密码
            //emsusr
            connectionFactory.setUsername("emsusr");
            connectionFactory.setPassword("emsusr");

            //获取连接对象
            Connection connection = connectionFactory.newConnection();
            //获取连接中的通道
            Channel channel = connection.createChannel();
            //通道绑定对应的消息队列
            //参数1 ：队列名称，不存在则自动创建
            //参数2 ：用来定义队列特征是否需要持久化。持久化之后mq重启，队列持久化磁盘，重新后恢复，不持久化，重启队列则删除
            //参数3 ：是否独占队列，false 不独占，
            //参数4 ：是否在消费之后自动删除队列，false 不自动删除
            //参数5 ：额外参数
            channel.queueDeclare("helloQueue", false, false, false, null);

            //参数1 ： 交换机名称
            //参数2 ： 队列名称  helloQueue
            //参数3 ： 消息的额外设置
            //参数4 ： 消息名称
            channel.basicPublish("", "helloQueue", null, "hello rabitmq!!!!!!".getBytes());
            channel.basicPublish("", "helloQueue", null, "hello rabitmq 1!!!!!!".getBytes());
            channel.basicPublish("", "helloQueue", null, "hello rabitmq 2!!!!!!".getBytes());

            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

}
