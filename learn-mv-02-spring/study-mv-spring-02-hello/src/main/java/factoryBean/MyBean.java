package factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/13
 */
public class MyBean implements FactoryBean<Phone> {
    public Phone getObject() throws Exception {
        Phone phone = new Phone();
        phone.setNum("110");
        return phone;
    }

    public Class<?> getObjectType() {
        return null;
    }
}
