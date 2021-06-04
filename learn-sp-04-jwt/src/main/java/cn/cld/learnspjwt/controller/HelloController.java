package cn.cld.learnspjwt.controller;

import cn.cld.learnspjwt.helps.JWTHelp;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/27
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String fun() {
        return "jwt";
    }

//                    .addPathPatterns("/**") //表示拦截的资源
//                .excludePathPatterns("/user/**"); //表示放行资源
    @RequestMapping("get/token")
    public String fun2(String username , String pwd) {
        Map<String,String> map = new HashMap<>();
        map.put("username" , username);
        String token = JWTHelp.getToken(map);
        return token;
    }
    @RequestMapping("user/get/token")
    public String fun5(String username , String pwd) {
        Map<String,String> map = new HashMap<>();
        map.put("username" , username);
        String token = JWTHelp.getToken(map);
        return token;
    }

    // 这种每个受保护的接口都得写验证，不好，通过拦截器统一实现
    @RequestMapping("get/resource")
    public Map<String, Object> fun3(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            JWTHelp.verify(token);
            map.put("msg", "验证通过~~~");
            map.put("state", true);
        } catch (TokenExpiredException e) {
            map.put("state", false);
            map.put("msg", "Token已经过期!!!");
        } catch (SignatureVerificationException e){
            map.put("state", false);
            map.put("msg", "签名错误!!!");
        } catch (AlgorithmMismatchException e){
            map.put("state", false);
            map.put("msg", "加密算法不匹配!!!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "无效token~~");
        }
        return map;
    }

    // 这种每个受保护的接口都得写验证，不好，通过拦截器统一实现
    @RequestMapping("get/resource2")
    public Map<String, Object> fun6(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "验证通过~~~");
        map.put("state", true);
        return map;
    }


}
