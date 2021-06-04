package cn.cld.learnspshiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/22
 */

@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }
    public static Object getBean(String name) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        return applicationContext.getBean(name);
    }

}
