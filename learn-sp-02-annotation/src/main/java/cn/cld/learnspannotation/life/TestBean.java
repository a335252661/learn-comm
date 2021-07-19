package cn.cld.learnspannotation.life;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
 */
@Component
public class TestBean implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean,afterPropertiesSet----------------------------------------------");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("TestBean,destroy----------------------------------------------");
    }
}
