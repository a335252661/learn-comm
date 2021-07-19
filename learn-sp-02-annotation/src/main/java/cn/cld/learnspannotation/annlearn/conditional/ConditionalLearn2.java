package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.bean.Person;
import cn.cld.learnspannotation.annlearn.conditionBean.LinuxCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/18
 */
@Conditional({LinuxCondition.class})
@Configuration
public class ConditionalLearn2 {


//    所以@Conditional标注在方法上只能控制一个bean实例是否注入。
//    @Conditional标注在类上就决定了一批bean是否注入。
    
    //只有一个类时，大括号可以省略
    //如果WindowsCondition的实现方法返回true，则注入这个bean
    @Bean(name = "bill")
    public Person person1(){
        return new Person("Bill Gates",62);
    }

    //如果LinuxCondition的实现方法返回true，则注入这个bean
    @Bean("linus")
    public Person person2(){
        return new Person("Linus",48);
    }
}
