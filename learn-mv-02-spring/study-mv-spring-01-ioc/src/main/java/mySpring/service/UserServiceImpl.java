package mySpring.service;

import dao.UserDao;
import dao.UserDaoImpl;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/3/11
 */
public class UserServiceImpl implements UserService{

    private UserDao userDao = new UserDaoImpl();

    public void getUser() {
        userDao.getUser();
    }
}
