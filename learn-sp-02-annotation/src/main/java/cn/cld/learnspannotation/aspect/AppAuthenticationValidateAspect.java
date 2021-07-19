package cn.cld.learnspannotation.aspect;

import cn.cld.learnspannotation.annotation.AppAuthenticationValidate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

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

        // 获取切入点方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName---------"+methodName);


        // 获取方法上的注解参数
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        AppAuthenticationValidate appAuthenticationValidate = signature.getMethod().getAnnotation(AppAuthenticationValidate.class);
        String[] strings = appAuthenticationValidate.requestParams();
        System.out.println(Arrays.asList(strings));



    }
}
