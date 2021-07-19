package cn.cld.learnspannotation.annlearn.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
@ConditionalOnProperty(
        value = "spring.cloud.siren.enabled" // 属性的值为true时才会被加载
        ,matchIfMissing = true // 如果该属性不存在也会被加载
)
public class ConditionalLearn3 {

    public ConditionalLearn3(){
        System.out.println("ConditionalLearn3---------------------加载");
    }
}
