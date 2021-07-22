import controller.UserController;
import org.junit.Test;
import service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/22
 */
public class MyTest {

    @Test
    public void fun() throws Exception{
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        //获取类 UserController 属性
        Field service22Field = clazz.getDeclaredField("userService22");
        //拿到了属性，最终的结果就是给属性赋值。我们 private UserService userService22; 是私有的
        //不对外提供共有的访问权限，所以需要通过getset方法来设置属性值

        //我们把属性的set方法设置成了private，所以我们需要 setAccessible(true) 来获取私有的访问权限
//        service22Field.setAccessible(true);

        //获取字段的名字
        String name = service22Field.getName();
        //获取set方法的名字
        name = name.substring(0,1).toUpperCase()+name.substring(1,name.length());
        String setMethodName = "set"+name;

        UserService userService = new UserService();

        //通过方法注入属性的对象   通过set方法 setUserService22(UserService userService22) ， 把UserService的对象创建出来
        // 第一个参数是方法名 ， 第二个参数是入参
        Method method = clazz.getMethod(setMethodName, UserService.class);
        method.invoke(userController, userService);

        System.out.println(userController.getUserService22());//打印属性对象

    }
}
