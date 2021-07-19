package mySpring.service;

import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
@Component
public class OrderService2Impl implements OrderService{
    public void queryName() {
        System.out.println("OrderService2Impl");
    }
}
