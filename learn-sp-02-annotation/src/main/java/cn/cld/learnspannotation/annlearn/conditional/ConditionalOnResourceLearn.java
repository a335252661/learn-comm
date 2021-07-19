package cn.cld.learnspannotation.annlearn.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
@ConditionalOnResource(resources = "/test.xml")
public class ConditionalOnResourceLearn {
    public ConditionalOnResourceLearn(){
        System.out.println("ConditionalOnResourceLearn---------------------加载");
    }
}
