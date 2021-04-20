package cn.cld.learndocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.cld.learndocker.*"})
public class LearnDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnDockerApplication.class, args);
    }

}
