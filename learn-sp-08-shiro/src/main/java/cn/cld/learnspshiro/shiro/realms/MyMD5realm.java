package cn.cld.learnspshiro.shiro.realms;

import cn.cld.learnspshiro.dao.UserDAO;
import cn.cld.learnspshiro.entity.User;
import cn.cld.learnspshiro.shiro.salt.MyByteSource;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/5
 */
public class MyMD5realm extends AuthorizingRealm {

    @Resource
    private UserDAO userDAO2;

    //自定义授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------------------------------------------------");
        String userName = principalCollection.getPrimaryPrincipal().toString();
        System.out.println("身份信息: " + userName);

        //根据身份信息-用户名查询用户的角色信息
        //将角色信息返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();


//        simpleAuthorizationInfo.addRole("admin");
//        simpleAuthorizationInfo.addRole("user");

        //将数据库中查询的权限信息赋值给权限对象  user:*:01 表示 用户拥有user角色下的对01用户所有的权限
//        simpleAuthorizationInfo.addStringPermission("user:query:*");
//        simpleAuthorizationInfo.addStringPermission("user:update:*");
//        simpleAuthorizationInfo.addStringPermission("user:delete:01");



        //查询数据库，查询用户的角色信息
        List<JSONObject> qwer = userDAO2.queryRoleByUserName(userName);
        if(!qwer.isEmpty()){
            qwer.forEach(jsonObject -> {
                //{"password":"36da1c38dabc1ff070d53a5e2a0f337a","salt":"D4bd&66)D6","roleid":2,"name":"user","id":2,"userid":2,"username":"qwer"}
                System.out.println(jsonObject.toJSONString());
                String roleName = jsonObject.get("name").toString();
                simpleAuthorizationInfo.addRole(roleName);


                List<JSONObject> jsonObjects = userDAO2.queryPermsByRoleName(roleName);
                //{"name":"user","id":2,"permsname":"user:query:*"}
                if(!jsonObjects.isEmpty()){
                    jsonObjects.forEach(js -> {
                        System.out.println(js.toJSONString());
                        String permsname = js.get("permsname").toString();
                        simpleAuthorizationInfo.addStringPermission(permsname);
                    });
                }

            });
        }


        return simpleAuthorizationInfo;
    }

    //自定义身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1、在token中获取用户名
        String principal = authenticationToken.getPrincipal().toString();
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;



//        //2、通过用户名到数据库查询密码
//        String pwd = "123";
//        //3、进行身份认证,MD5加密  加salt  ，散列1024次数
//        Md5Hash md5Hash = new Md5Hash(pwd, "6eA@!$E(G!", 1024);
//        String md5pwd = md5Hash.toHex();
//        //添加随机salt
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, md5pwd, ByteSource.Util.bytes("X0*7psx"), this.getName());



        User user = userDAO2.queryByName(principal);
        if(!ObjectUtils.isEmpty(user)){
            String password = user.getPassword();
            String salt = user.getSalt();
//             simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes(salt), this.getName());
             simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, password, new MyByteSource(salt), this.getName());

            System.out.println("realm 认证的用户名："+principal);
            System.out.println("realm 认证的 密码 ："+password);

        }



        return simpleAuthenticationInfo;
    }
}
