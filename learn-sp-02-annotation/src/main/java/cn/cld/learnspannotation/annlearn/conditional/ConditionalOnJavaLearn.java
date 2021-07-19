package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.life.TestSmartLifecycle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
@Configuration
@ConditionalOnJava(JavaVersion.EIGHT) //只有java1.8版本才加载这个
public class ConditionalOnJavaLearn {
    public ConditionalOnJavaLearn(){
        System.out.println("ConditionalOnJavaLearn---------------------加载");
    }
}
