package cn.cld.learnspannotation.invocationHandler.HelloDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
public class DynaProxyHello implements InvocationHandler {
    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(
                this.delegate.getClass().getClassLoader(), this.delegate
                        .getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        try {
            System.out.println("问候之前的日志记录...");
            // JVM通过这条语句执行原来的方法(反射机制)
            result = method.invoke(this.delegate, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        DynaProxyHello helloproxy = new DynaProxyHello();
        Helloimplements hello = new Helloimplements();
        IHello ihello = (IHello) helloproxy.bind(hello);
        ihello.sayHello("Jerry");
    }

}
