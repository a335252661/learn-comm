package aopanno;

import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
@Component
public class User {
    public void add() {   // 这个是需要被增强的方法，叫做切入点 aopanno.User.add
        System.out.println("我是add方法--------------");
    }
}
