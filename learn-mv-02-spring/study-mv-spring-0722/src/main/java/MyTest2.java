import controller.MyAutowired;
import controller.UserController;
import org.junit.Test;
import service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/22
 */
public class MyTest2 {

    @Test
    public void fun() throws Exception{
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Stream.of( clazz.getDeclaredFields() ).forEach( field -> {
            String name = field.getName(); //获取属性名
            //获取属性上面的注解
            MyAutowired annotation = field.getAnnotation(MyAutowired.class);
            if(annotation != null ){
                field.setAccessible(true);
                //获取属性的类型 ，
                Class<?> type = field.getType();
                try {
                    Object o = type.newInstance();

                    field.set(userController , o);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getPersonService());

    }
}
