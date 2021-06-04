package cn.cld.learnspjwt.interceptor;

import cn.cld.learnspjwt.helps.JWTHelp;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/4/28
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {
    //我们现在只是创建了一个自己的拦截器 JWTInterceptor ，需要向总的拦截器中去注册
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头中获取携带的token
        String token = request.getHeader("token");
        Map<String,Object> map = new HashMap<>();
        try {
            JWTHelp.verify(token);
            return true;
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
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
