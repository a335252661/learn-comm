package cn.cld.learnspannotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/5/7
 */
@Aspect
@Component
public class AppAuthenticationValidateAspect {

    // 切点
    @Pointcut("@annotation(cn.cld.learnspannotation.annotation.AppAuthenticationValidate)") // 所有带有 Log 注解的方法作为 织入点
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void fun(JoinPoint joinPoint) {
        System.out.println("----------Before");
        Object[] args = joinPoint.getArgs();

        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (Object arg : args) {
            System.out.println(arg);

        }

        for (String parameterName : parameterNames) {
            System.out.println(parameterName);
        }
    }
}
