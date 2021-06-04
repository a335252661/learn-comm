package cn.cld.learnspannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class LearnSpAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpAnnotationApplication.class, args);
    }

}
