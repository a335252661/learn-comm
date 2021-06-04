package cn.cld.learnspcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="cn.cld.learnspcode.dao")
public class LearnSp11EasycodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSp11EasycodeApplication.class, args);
    }

}
