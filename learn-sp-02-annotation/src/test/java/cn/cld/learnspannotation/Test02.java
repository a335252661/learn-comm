package cn.cld.learnspannotation;

import cn.cld.learnspannotation.bean.Hello;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/30
 * spring 扩展点02
 */
public class Test02 extends AbstractRefreshableApplicationContext {


    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
//        super.customizeBeanFactory(beanFactory);
        beanFactory.setAllowBeanDefinitionOverriding(false);
        beanFactory.setAllowCircularReferences(false);
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        System.out.println(2);
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("testBean.xml");
        //        //我们的对象现在都在Spring中的管理了，我们需要使用，直接去里面取出来就可以！
        Hello hello = (Hello) classPathXmlApplicationContext.getBean("myhello");
        System.out.println(hello.toString());
        //我想获取当前bean在ioc容器里面的名字
        String beanName = hello.getBeanName();
        System.out.println("bean在ioc容器里面的名字==="+beanName);
    }
}
