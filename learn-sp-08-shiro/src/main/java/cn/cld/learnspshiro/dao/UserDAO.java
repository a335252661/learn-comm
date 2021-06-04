package cn.cld.learnspshiro.dao;

import cn.cld.learnspshiro.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/7
 */
//@Mapper
//@Repository
public interface UserDAO {

    String queryid(User user);

    User test2();

    int saveUser(User user);

    User queryByName(@Param("username") String principal);

    List<JSONObject> queryRoleByUserName(@Param("username") String username);

    List<JSONObject> queryPermsByRoleName(@Param("roleName") String roleName);

}
