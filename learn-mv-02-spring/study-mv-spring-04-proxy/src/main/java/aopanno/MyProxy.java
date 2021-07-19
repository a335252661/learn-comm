package aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
@Order(2)
public class MyProxy {

    //抽取相同切入点
    @Pointcut(value = "execution(* aopanno.User.add(..))")
    public void pointdemo() {}

    // 1、前置通知
    @Before(value = "pointdemo()")
    public void before() {
        System.out.println("前置通知  before  @Order(2)  ............");
    }

    // 权限修饰符   返回类型（可以省略）   类全路径。方法（。。）
    @After(value = "execution(* aopanno.User.add(..))")
    public void after() {
        System.out.println("后置通知  after  @Order(2)............");
    }


    @Around(value = "execution(* aopanno.User.add(..))")
    public void Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前  @Order(2)............");
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后  @Order(2)............");
    }

    @AfterReturning(value = "execution(* aopanno.User.add(..))")
    public void AfterReturning() {
        System.out.println("AfterReturning  在返回值之后执行  @Order(2)............");
    }

    @AfterThrowing(value = "execution(* aopanno.User.add(..))")
    public void AfterThrowing() {
        System.out.println("AfterThrowing  异常通知，当执行的方法有异常则执行此方法  @Order(2)............");
    }

}
