package controller;

import service.PersonService;
import service.UserService;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/22
 */
public class UserController {
    //在spring中，添加@Autowired 注解，自动注入对象
    //我们自己实现
    //我们这个地方只定义了一个引用
    private UserService userService22;
    public UserService getUserService22() {
        return userService22;
    }
    public void setUserService22(UserService userService22) {
        this.userService22 = userService22;
    }

    //我们自定义我们的注解，添加在字段上
    //和set方法没有关系
    @MyAutowired
    private PersonService personService;
    public PersonService getPersonService() {
        return personService;
    }
}
