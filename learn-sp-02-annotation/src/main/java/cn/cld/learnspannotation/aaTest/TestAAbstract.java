package cn.cld.learnspannotation.aaTest;

import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/30
 */
public abstract class TestAAbstract extends AbstractApplicationContext {

    @Override
    protected void initPropertySources() {
        super.initPropertySources();
        System.out.println("扩展initPropertySource");
        //这里添加了一个name属性到Environment里面，以方便我们在后面用到
        getEnvironment().getSystemProperties().put("name","bobo");
        //这里要求Environment中必须包含username属性，如果不包含，则抛出异常
        getEnvironment().setRequiredProperties("username");
    }


    protected String  myobtainFreshBeanFactory2() {
        refreshBeanFactory2();
        return "getBeanFactory()";
    }

    protected abstract void refreshBeanFactory2() throws BeansException, IllegalStateException;

    public static void main(String[] args) {
        TestAAbstract testAAbstract = new TestAson1();
        testAAbstract.myobtainFreshBeanFactory2();
    }


}
