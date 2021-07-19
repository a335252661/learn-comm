package mySpring.controller;

import mySpring.service.OrderService2Impl;
import mySpring.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import mySpring.service.OrderService;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
@Component
public class UserController {

    @Autowired
    @Qualifier(value = "orderService2Impl")
    private OrderService orderService22;
    public void fun() {
        orderService22.queryName();
    }


//    @Resource //当有两个实现类，根据类型注入肯定出错，
//    @Resource(name = "orderService2Impl" )  //这个是根据名称注入
//    @Resource(type = OrderService.class )  //这个是根据Type注入
    @Resource(type = OrderServiceImpl.class,name = "orderServiceImpl" )
    private OrderService orderService11;
    public void fun2() {
        orderService11.queryName();
    }

}
