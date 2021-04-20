package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import shiro.realm.MyMD5realm;
import shiro.realm.Myrealm;

import java.util.Arrays;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/5
 */
public class TestMD5Authenticator {


    public static void main(String[] args) {
        //1、最核心的就是安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //2、给安全管理器设置realm  realm 就是正确的账户信息，数据的调配
//        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
//        defaultSecurityManager.setRealm(new Myrealm());
        //设置realm 使用MD5凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //告诉realm使用了什么算法
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //告诉realm散列的次数，默认是散列一次
        hashedCredentialsMatcher.setHashIterations(1024);


        MyMD5realm myMD5realm = new MyMD5realm();
        myMD5realm.setCredentialsMatcher(hashedCredentialsMatcher);
        defaultSecurityManager.setRealm(myMD5realm);

        //3、给全局的安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //4、获取subject主体，就是一个用户
        Subject subject = SecurityUtils.getSubject();
        //5、创建令牌（就是账户和密码的包装类）
        UsernamePasswordToken token = new UsernamePasswordToken("xiaocheng", "123");
//        UsernamePasswordToken token = new UsernamePasswordToken("xiaocheng", "123X0*7psx");
        //6、用户使用令牌登陆
        try {
            System.out.println("认证状态： " + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态： " + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }

        //用户身份信息认证通过，做授权操作
        if (subject.isAuthenticated()) {

            //判断用户是否拥有角色的权限
            System.out.println(subject.hasRole("admin"));
            //判断用户是否拥有多个角色
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));
            //是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }


            //基于权限字符串的访问控制，资源标识符:操作类型：资源类型（资源实例）
            System.out.println("基于权限字符串的访问控制，资源标识符:操作类型：资源类型");
            System.out.println(subject.isPermitted("user:*:*"));
            System.out.println(subject.isPermitted("product:create:banana"));
            //分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:*", "product:create:banana");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            //同时具有哪些权限
            System.out.println(subject.isPermittedAll("super:*:01", "product:create:banana"));


        }

    }
}
