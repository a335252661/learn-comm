package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import shiro.realm.Myrealm;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/5
 */
public class TestSQLAuthenticator {


    public static void main(String[] args) {
        //1、最核心的就是安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //2、给安全管理器设置realm  realm 就是正确的账户信息，数据的调配
//        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        defaultSecurityManager.setRealm(new Myrealm());
        //3、给全局的安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //4、获取subject主体，就是一个用户
        Subject subject = SecurityUtils.getSubject();
        //5、创建令牌（就是账户和密码的包装类）
        UsernamePasswordToken token = new UsernamePasswordToken("xiaocheng", "123");
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
    }
}
