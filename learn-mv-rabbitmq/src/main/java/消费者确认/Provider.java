package 消费者确认;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import help.RabbitMQHelp;
import org.junit.Test;

import java.io.IOException;

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
            channel.queueDeclare("testConfirmQueue", false, false, false, null);

            //表示开启消息确认模式
            channel.confirmSelect();

            // 普通Confirm模式
//            fun1(channel);

            //批量Confirm模式
            fun2(channel);

            // 批量异步确认
//            fun3(channel);

//            RabbitMQHelp.closeMQ();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fun3(Channel channel) throws IOException {
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "testConfirmQueue", null, ("hello rabitmq!!!!!!" + "   " + i).getBytes());
        }
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                //deliveryTag；唯一消息标签
                //multiple：是否批量
                System.err.println("-------no ack!-----------");
            }

            //表示队列收到消息成功，并且将ack返回给生产者
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("表示队列收到消息成功，并且将ack返回给生产者");
            }
        });
    }

    private void fun2(Channel channel) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "testConfirmQueue", null, ("hello rabitmq!!!!!!" + "   " + i).getBytes());
        }
        channel.waitForConfirmsOrDie(); //直到所有信息都发布，只要有一个未确认就会IOException
        System.out.println("queue所有消息都收到了");
    }

    private void fun1(Channel channel) throws IOException, InterruptedException {
        //参数1 ： 交换机名称
        //参数2 ： 队列名称  helloQueue  交换机通过routingkey 将消息发送到对应的queue上去
        //参数3 ： 消息的额外设置
        //参数4 ： 消息名称
        channel.basicPublish("", "testConfirmQueue", null, ("hello rabitmq!!!!!!" + "   ").getBytes());
        // 1、 普通Confirm模式
        if (channel.waitForConfirms()) {
            System.out.println("表示队列收到消息成功，并且将ack返回给生产者");
        }
    }

}
