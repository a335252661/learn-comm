package cn.cld.Controller.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/1/17
 */
@Component
public class directController {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//不指定名字就是临时队列
                    exchange = @Exchange(value = "logs_sp_direct", type = "direct"), //绑定交换机，指定交换机的类型
                    key = {"info", "warning", "err"}
            )
    })
    public void fun(String mess) {
        System.out.println("消费者1 ： mess = " + mess);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//不指定名字就是临时队列
                    exchange = @Exchange(value = "logs_sp_direct", type = "direct"), //绑定交换机，指定交换机的类型
                    key = {"info"}
            )
    })
    public void fun2(String mess) {
        System.out.println("消费者2 ： mess = " + mess);
    }

}
