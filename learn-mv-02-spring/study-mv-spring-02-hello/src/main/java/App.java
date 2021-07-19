import beans.User;
import beans2.Person;
import beans3.Orders;
import factoryBean.Phone;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/11
 */
public class App {
    public static void main(String[] args) {
//        //获取Spring的上下文对象！
        ApplicationContext context = new ClassPathXmlApplicationContext("testBean.xml");
//
//        //我们的对象现在都在Spring中的管理了，我们需要使用，直接去里面取出来就可以！
        Hello hello = (Hello) context.getBean("myhello");
        System.out.println(hello.toString());
//
//        //    <bean id="myhello" class="Hello">
//        //        <property name="str" value="Spring"/>
//        //    </bean>
//        //     Hello myhello = new Hello();
//        //     myhello.setStr = "Spring"
//
//
//         User userVo = (User) context.getBean("userVo");
//        System.out.println(userVo.toString());
//
//        Person person = context.getBean("person", Person.class);
//        System.out.println(person);


        //获取Spring的上下文对象！
//        ApplicationContext contextorder = new ClassPathXmlApplicationContext("beans3.xml");
//        Orders order = contextorder.getBean("order", Orders.class);
//        System.out.println("第四步：获取bean实例对象");
//        System.out.println(order);

        //获取Spring的上下文对象！
        ApplicationContext factoryBeanContext = new ClassPathXmlApplicationContext("factoryBean.xml");
        Phone phone = factoryBeanContext.getBean("mybean", Phone.class);
        System.out.println(phone.getNum());

    }


}
