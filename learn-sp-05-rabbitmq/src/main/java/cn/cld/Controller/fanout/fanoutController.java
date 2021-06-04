package cn.cld.Controller.fanout;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
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
public class fanoutController {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//不指定名字就是临时队列
                    exchange = @Exchange(value = "logs_sp_fanout", type = "fanout") //绑定交换机，指定交换机的类型
            )
    })
    public void fun(String mess) {
        System.out.println("消费者1 ： mess = " + mess);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//不指定名字就是临时队列
                    exchange = @Exchange(value = "logs_sp_fanout", type = "fanout") //绑定交换机，指定交换机的类型
            )
    })
    public void fun2(String mess) {
        System.out.println("消费者2 ： mess = " + mess);
    }

}
