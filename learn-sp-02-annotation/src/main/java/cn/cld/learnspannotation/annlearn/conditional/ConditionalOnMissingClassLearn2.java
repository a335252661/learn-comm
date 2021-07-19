package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.life.TestSmartLifecycle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
//@ConditionalOnMissingClass(value ="TestSmartLifecycle22.class") // TestSmartLifecycle22 这个class不存在，那么加载这个bean
@ConditionalOnMissingClass(value ="TestSmartLifecycle.class") // TestSmartLifecycle 这个class存在，那么不加载这个bean
public class ConditionalOnMissingClassLearn2 {
    public ConditionalOnMissingClassLearn2(){
        System.out.println("ConditionalOnMissingClassLearn2---------------------加载");
    }
}
