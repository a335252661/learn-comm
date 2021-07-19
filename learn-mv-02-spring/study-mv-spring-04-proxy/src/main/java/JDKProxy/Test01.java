package JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
public class Test01 {

    public static void main(String[] args) {

        final UserService userService =  new UserServiceImpl();
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        System.out.println(interfaces);


        Object o = Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("1--------------------");
                        Object invoke = method.invoke(userService, args);
                        System.out.println("2--------------------");
                        return invoke;
                    }
                });


        UserService userService1 = (UserService)o;
        userService1.login();

    }

}
