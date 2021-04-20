package cn.cld.learnspshiro.service;

import cn.cld.learnspshiro.dao.UserDAO;
import cn.cld.learnspshiro.entity.User;
import cn.cld.learnspshiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
@Service
@Transactional
public class UserImpl implements UserService{

    @Resource
    private UserDAO userDAO;

    @Override
    public int register(User user) {

        String salt = SaltUtils.getSalt(10);

        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);

        user.setPassword(md5Hash.toHex());


        User user2 = userDAO.test2();
        int id = userDAO.saveUser(user);
        return id;
    }
}
