package servicenew;

import dao.UserDao;
import dao.UserDaoImpl;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/11
 */
public class UserNewServiceImpl implements UserNewService {

    private UserDao userDao2;
    //利用set进行动态实现值的注入！
    public void setUserDao(UserDao userDao) {
        this.userDao2 = userDao;
    }

    public void getUser() {
        userDao2.getUser();
    }

}
