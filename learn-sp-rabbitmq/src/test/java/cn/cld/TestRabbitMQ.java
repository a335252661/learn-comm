package cn.cld;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/17
 */
@SpringBootTest
public class TestRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 1、模式1
     */
    @Test
    public void fun() {
        //路由key是 hello ，发送的消息是 hello msg
        rabbitTemplate.convertAndSend("hello", "hello msg");
    }

    /**
     * 1、模式2 ，
     */
    @Test
    public void fun2() {
//        10.for
        for (int i = 0; i < 20; i++) {
            //路由key是 hello ，发送的消息是 hello msg
            rabbitTemplate.convertAndSend("work", "hello msg :" + i);
        }


        //开启生产者确认机制 ， 确保消息发送到mq
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback(){
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("CorrelationData content : " + correlationData);
                System.out.println("Ack status : " + ack);
                System.out.println("Cause content : " + cause);
                if(ack){
                    System.out.println("消息成功发送，订单入库，更改订单状态");
                }else{
                    System.out.println("消息发送失败：" + correlationData + ", 出现异常：" + cause);
                }
            }
        });

        //被退回来的消息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("被退回的消息为：{}" + message);
        });

    }

    /**
     * 1、模式3，广播模式
     */
    @Test
    public void fun3() {
        //路由key是 hello ，发送的消息是 hello msg
        rabbitTemplate.convertAndSend("logs_sp_fanout", "", "fanout 模式的 发送");
    }

    /**
     * 1、模式4，路由模式
     */
    @Test
    public void fun4() {
        //路由key是 hello ，发送的消息是 hello msg
        rabbitTemplate.convertAndSend("logs_sp_direct", "warning", "direct 模式的 发送");
    }

    /**
     * 1、模式5，动态路由模式
     */
    @Test
    public void fun5() {
        //路由key是 hello ，发送的消息是 hello msg
        rabbitTemplate.convertAndSend("logs_sp_topic", "some.info.test", "topic 模式的 发送");
    }

}
