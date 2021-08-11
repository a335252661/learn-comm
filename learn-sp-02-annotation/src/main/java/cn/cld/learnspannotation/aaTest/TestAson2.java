package cn.cld.learnspannotation.aaTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/30
 */
public class TestAson2 extends TestAAbstract{
    @Override
    protected void refreshBeanFactory2() throws BeansException, IllegalStateException {
        System.out.println("TestAson2--------------");
    }

    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {

    }

    @Override
    protected void closeBeanFactory() {

    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return null;
    }
}
