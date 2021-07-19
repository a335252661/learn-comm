package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.life.TestSmartLifecycle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
@ConditionalOnClass(TestSmartLifecycle.class) //只有在类路径中有某个class时，才加载给定的bean：
public class ConditionalOnClassLearn {
    public ConditionalOnClassLearn(){
        System.out.println("ConditionalOnClassLearn---------------------加载");
    }
}
