package cn.cld.Controller;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/17
 */
@Component //作为bean被springboot扫描到
//作为消费者，在监听 queuesToDeclare 从队列中获取消息  hello
//默认创建的持久化的队列
//@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "false",exclusive = "",autoDelete = "false"))
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloMQController {


    // 获取到消息的时候的回调函数
    @RabbitHandler
    public void fun(String message) {
        System.out.println(message);
    }

}
