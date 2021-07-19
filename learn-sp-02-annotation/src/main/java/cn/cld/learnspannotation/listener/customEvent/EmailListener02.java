package cn.cld.learnspannotation.listener.customEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/24
 */
@Component
public class EmailListener02 implements ApplicationListener<EmailEvent> {
    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("事件被监听到--------EmailListener02");
        System.out.println(event.getAddress());
    }
}
