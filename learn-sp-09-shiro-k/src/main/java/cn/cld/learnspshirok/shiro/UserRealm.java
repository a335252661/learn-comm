package cn.cld.learnspshirok.shiro;

import cn.cld.learnspshirok.entity.MyUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/28
 */
public class UserRealm extends AuthorizingRealm {
    //给用户授权，添加xx权限
    // 如果在config 配置里面给某个请求 添加了需要权限才能访问 map.put("/user/add" , "perms[user:add]"); ，
    // 那么每点击一次就会调用一次这个方法 doGetAuthorizationInfo
    // 而且 html里面 shiro:hasPermission  每有一次就会调用一次
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------------------------------------------------授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        MyUser currentUser = (MyUser)subject.getPrincipal();
        // 不同的用户添加不同的权限
        if("root".equals(currentUser.getName())){
            simpleAuthorizationInfo.addStringPermission("user:add");
        }

        return simpleAuthorizationInfo;
    }

    //认证 验证 ，验证用户身份是否正确
    // //通过toLogin接口跳转到login页面，提交表单，到达登陆接口
    // 登陆接口调用subject.login  方法进行登陆
    // 就会调用 realm里面的认证方法doGetAuthenticationInfo会进行验证拦截，判断用户和密码
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-----------------------------------------------------认证");
        MyUser myUser = new MyUser();
        String name = "root";
        String pwd = "root";
        myUser.setName(name);
        myUser.setPwd(pwd);
        //连接真实的数据库

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        //从UsernamePasswordToken中获取在controller中存入的 用户输入的用户名
        String username = usernamePasswordToken.getUsername();
        // return null;  就会抛出异常 ， UnknownAccountException用户不存在
        //1、用户名判断
        if(!username.equals(name)){
            return null;
        }
        // 这个地方存进去用户  principal  --  User ,就可以通过 SecurityUtils 来取了
        //         Subject subject = SecurityUtils.getSubject();
        //        User principal = (User)subject.getPrincipal();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(myUser, pwd, "");
        return simpleAuthenticationInfo;
    }
}
