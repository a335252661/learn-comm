package beans3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
public class MybeanPost implements BeanPostProcessor {

    // Process 过程  Initialization 初始化
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("前置处理器");

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("前后置处理器");

        return bean;
    }

}
