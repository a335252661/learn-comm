package cn.cld.learnspannotation.bean;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/11
 */
public class Hello implements BeanNameAware {
    private String str;
    private  String beanName ;

    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }


    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
    public String getBeanName() {
        return beanName;
    }


    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
