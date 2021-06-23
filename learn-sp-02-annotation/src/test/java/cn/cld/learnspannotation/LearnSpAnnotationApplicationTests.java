package cn.cld.learnspannotation;

import cn.cld.learnspannotation.annlearn.ConditionalLearn;
import cn.cld.learnspannotation.annlearn.ConditionalLearn2;
import cn.cld.learnspannotation.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

}
