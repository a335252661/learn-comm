package cn.cld.learnspannotation.annlearn.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
//如果我们需要基于多个参数的复杂条件，我们可以使用@ConditionalOnExpression：
@Configuration
@ConditionalOnExpression(
        "${spring.cloud.siren.enabled:true} and ${spring.cloud.siren2.enabled:true}"
)
public class ConditionalOnExpressionLearn {
    public ConditionalOnExpressionLearn(){
        System.out.println("ConditionalOnExpressionLearn---------------------加载");
    }
}
