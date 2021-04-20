package cn.cld.Controller.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/17
 */
@Component
public class workController {

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void fun(String mess) {
        System.out.println("消费者01 ： mess = " + mess);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void fun2(String mess) {
        System.out.println("消费者02 ： mess = " + mess);
    }

}
