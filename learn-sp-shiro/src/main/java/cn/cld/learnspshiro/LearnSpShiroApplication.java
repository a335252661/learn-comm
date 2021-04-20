package cn.cld.learnspshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"cn.cld.*"})
// 可以加在 启动类上   @MapperScan({"cn.cld.learnspshiro.dao"})   或者在 dao位置也就是mapper接口位置 上加上注解mapper
@MapperScan(basePackages = "cn.cld.learnspshiro.dao")
@SpringBootApplication
public class LearnSpShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpShiroApplication.class, args);
    }

}
