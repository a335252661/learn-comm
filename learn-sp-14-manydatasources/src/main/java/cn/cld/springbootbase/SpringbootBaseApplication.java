package cn.cld.springbootbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan({"cn.cld.mapper"})
@SpringBootApplication(scanBasePackages = {"cn.cld.*"})
public class SpringbootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseApplication.class, args);
    }

}
