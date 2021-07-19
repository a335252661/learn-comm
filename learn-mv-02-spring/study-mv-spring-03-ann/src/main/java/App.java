import mySpring.config.SpringConfig;
import mySpring.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
public class App {
    public static void main(String[] args) {
        //        //获取Spring的上下文对象！
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        //我们的对象现在都在Spring中的管理了，我们需要使用，直接去里面取出来就可以！
//        UserController controller = (UserController) context.getBean("userController");
//        controller.fun();
//        controller.fun2();

        ApplicationContext annContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController controller = (UserController) annContext.getBean("userController");
//        controller.fun();
        controller.fun2();
    }


}
