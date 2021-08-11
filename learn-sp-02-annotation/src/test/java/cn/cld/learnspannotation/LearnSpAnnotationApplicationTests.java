package cn.cld.learnspannotation;

import cn.cld.learnspannotation.aaTest.TestAAbstract;
import cn.cld.learnspannotation.annlearn.conditional.ConditionalLearn2;
import cn.cld.learnspannotation.bean.Hello;
import cn.cld.learnspannotation.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

@SpringBootTest
class LearnSpAnnotationApplicationTests {

    @Test
    void contextLoads() {

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalLearn.class);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalLearn2.class);
        String osName = applicationContext.getEnvironment().getProperty("os.name");
        System.out.println("当前系统为：" + osName);

        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }


    @Test
    public void fun() {
//        //获取Spring的上下文对象！
        ApplicationContext context = new ClassPathXmlApplicationContext("testBean.xml");
//        //我们的对象现在都在Spring中的管理了，我们需要使用，直接去里面取出来就可以！
        Hello hello = (Hello) context.getBean("myhello");
        System.out.println(hello.toString());
    }

}
