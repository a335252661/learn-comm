package cn.cld.learnspannotation.annlearn;

import cn.cld.learnspannotation.service.Fruit;
import cn.cld.learnspannotation.service.impl.apple;
import cn.cld.learnspannotation.service.impl.orange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/18
 */
@Configuration
@Slf4j
public class ConditionalOnMissingBeanLearn {

    public ConditionalOnMissingBeanLearn(){
        log.info("ConditionalOnMissingBeanLearn------init");
    }

    class Person{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    @Bean
    @ConditionalOnBean(Person.class)
    @ConditionalOnMissingBean
    public Person person1() {
        return new Person();
    }
    @Bean
    @ConditionalOnBean(Person.class)
    @ConditionalOnMissingBean
    public Person person2() {
        return new Person();
    }

    @Order(2)
    @Bean
    @ConditionalOnMissingBean
    public Fruit appleTest01() {
        apple app = new apple();
        return app;
    }

    @Order(1)
    @Bean
    @ConditionalOnMissingBean
    public Fruit orangeTest01() {
        orange app = new orange();
        return app;
    }


}
