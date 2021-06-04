package cn.cld.learnspshiro.shiro.realms;

import cn.cld.learnspshiro.dao.UserDAO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/5
 */
public class Myrealm extends AuthorizingRealm {

    @Autowired
    private UserDAO userDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //自定义身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1、在token中获取用户名
        String principal = authenticationToken.getPrincipal().toString();

        //2、通过用户名到数据库查询密码
        String pwd = "123";
        //3、进行身份认证
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, pwd, this.getName());

        return simpleAuthenticationInfo;
    }
}
