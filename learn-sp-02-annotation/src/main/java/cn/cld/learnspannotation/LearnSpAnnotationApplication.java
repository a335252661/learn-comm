package cn.cld.learnspannotation;

import cn.cld.learnspannotation.annlearn.ConfigurationLearn;
import cn.cld.learnspannotation.bean.Car;
import cn.cld.otherConfig.OtherConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Import(value = {OtherConfiguration.class})//导入OtherConfiguration，加载第三方配置
public class LearnSpAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpAnnotationApplication.class, args);
    }

//    @Bean
//    public void fun() {
//        // @Configuration注解的spring容器加载方式，
//        // 用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
//        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationLearn.class);
//        Car car = (Car)context.getBean("car");
//        System.out.println("-----------------------------------------------------");
//        System.out.println(car.getName());
//    }

}
