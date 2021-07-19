package cn.cld.learnspannotation.invocationHandler;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
public class WorkHandler implements InvocationHandler {

    private Object obj;
    private Object obj2;
    private String name;

    //构造函数，给我们的真实对象赋值
    public WorkHandler(Object obj,Object obj2,String name) {
        this.obj = obj;
        this.obj2 = obj2;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        //在真实的对象执行之前我们可以添加自己的操作
        System.out.println("before invoke。。。");
        Object invoke = method.invoke(obj, objects);
        //在真实的对象执行之后我们可以添加自己的操作
        System.out.println("after invoke。。。");
        return invoke;
    }
}
