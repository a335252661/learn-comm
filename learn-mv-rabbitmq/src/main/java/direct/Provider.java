package direct;

import com.rabbitmq.client.Channel;
import help.RabbitMQHelp;
import org.junit.Test;

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
            //将通道声明指定交换机
            //参数1：指定交换机的名称
            //参数2：指定交换机的类型，广播模式(fanout) , 直连，也就是路由模式（direct）
            channel.exchangeDeclare("logs_direct_exchange", "direct");
            //发送消息
            channel.basicPublish("logs_direct_exchange", "info", null,
                    "info 的路由key ： direct type message".getBytes());

            //发送消息
            channel.basicPublish("logs_direct_exchange", "err", null,
                    "err 的路由key ： direct type message".getBytes());

            //发送消息
            channel.basicPublish("logs_direct_exchange", "warning", null,
                    "warning 的路由key ： direct type message".getBytes());

            RabbitMQHelp.closeMQ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
