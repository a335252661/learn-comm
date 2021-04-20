package shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/5
 */
public class MyMD5realm extends AuthorizingRealm {
    //自定义授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------------------------------------------------");
        String userName = principalCollection.getPrimaryPrincipal().toString();
        System.out.println("身份信息: " + userName);

        //根据身份信息-用户名查询用户的角色信息
        String admin = "admin";

        //将角色信息返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(admin);
        simpleAuthorizationInfo.addRole("user");

        //将数据库中查询的权限信息赋值给权限对象  user:*:01 表示 用户拥有user角色下的对01用户所有的权限
        simpleAuthorizationInfo.addStringPermission("super:*:01");
        simpleAuthorizationInfo.addStringPermission("product:*:*");

        return simpleAuthorizationInfo;
    }

    //自定义身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1、在token中获取用户名
        String principal = authenticationToken.getPrincipal().toString();

        //2、通过用户名到数据库查询密码
        String pwd = "123";
        //3、进行身份认证,MD5加密  加salt  ，散列1024次数
        Md5Hash md5Hash = new Md5Hash(pwd, "X0*7psx", 1024);
        String md5pwd = md5Hash.toHex();
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, md5pwd, this.getName());
        //添加随机salt
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, md5pwd, ByteSource.Util.bytes("X0*7psx"), this.getName());

        return simpleAuthenticationInfo;
    }
}
