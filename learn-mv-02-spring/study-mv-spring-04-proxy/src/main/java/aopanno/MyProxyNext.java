package aopanno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
@Component
@Aspect
@Order(1)
public class MyProxyNext {


    //抽取相同切入点
    @Pointcut(value = "execution(* aopanno.User.add(..))")
    public void pointdemo() {}

    @Before(value = "pointdemo()")
    public void before() {
        System.out.println("before  @Order(1)  ............");
    }

}
