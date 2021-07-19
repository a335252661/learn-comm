package cn.cld.learnspannotation.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
public class CommonInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result=null;
        System.out.println("CommonInterceptor----------------------------------");
        try{
            System.out.println("方法执行之前："+methodInvocation.getMethod().toString());

            result= methodInvocation.proceed();   //方法开始执行

            System.out.println("方法执行之后："+methodInvocation.getMethod().toString());
            System.out.println("方法正常运行结果："+result);

            return result;

        }catch (Exception e){
            System.out.println("方法出现异常:"+e.toString());
            System.out.println("方法运行Exception结果："+result);
            return result;
        }
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.setTarget("22");
        proxyFactory.addAdvice(new CommonInterceptor());

        Object proxy = proxyFactory.getProxy();
        CommonInterceptor methodInterceptor = (CommonInterceptor) proxy;

        methodInterceptor.doSomeThing("通过代理工厂设置代理对象，拦截代理方法");
    }
    public String doSomeThing(String someThing){
        //int i=5/0;
        return "执行被拦截的方法："+someThing;
    }
}
