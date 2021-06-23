package cn.cld.learnspannotation.annlearn;

import cn.cld.learnspannotation.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/10
 */
// Configuration 表示这是一个配置类，可替换xml配置文件
// 被注解的类内部包含有一个或多个被@Bean注解的方法，
// 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
// 并用于构建bean定义，初始化Spring容器。
@Configuration
public class ConfigurationLearn {


    public ConfigurationLearn(){
        System.out.println("ConfigurationLearn容器启动初始化。。。");
    }

    @Bean
    public Car addCarsome() {
        Car car = new Car();
        car.setName("name");
        return car;
    }
}
