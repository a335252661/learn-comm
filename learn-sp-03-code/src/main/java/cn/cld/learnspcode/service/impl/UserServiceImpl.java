package cn.cld.learnspcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cld.learnspcode.dao.UserDao;
import cn.cld.learnspcode.entity.User;
import cn.cld.learnspcode.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-04-29 14:03:56
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
