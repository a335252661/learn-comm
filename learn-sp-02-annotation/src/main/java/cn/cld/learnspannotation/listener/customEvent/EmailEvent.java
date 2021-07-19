package cn.cld.learnspannotation.listener.customEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/24
 */
public class EmailEvent extends ApplicationEvent {

    private String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public EmailEvent(Object source) {
        super(source);
    }
}
