package cn.cld.learnspannotation.annlearn.conditional;

import cn.cld.learnspannotation.life.TestSmartLifecycle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/6/28
 */
//只有在应用上下文中有TestSmartLifecycle这个类的bean存在时，ConditionalOnBeanLearn才会被加载。除了用bean class外，我们也可以使用bean的名称(bean name)。
//
//这样，我们可以定义特定模块间的依赖。只有在一模块的某个特定bean存在时，另一个模块才允许被加载。
@Configuration
@ConditionalOnBean(TestSmartLifecycle.class)
public class ConditionalOnBeanLearn {
    public ConditionalOnBeanLearn(){
        System.out.println("ConditionalOnBeanLearn---------------------加载");
    }
}
