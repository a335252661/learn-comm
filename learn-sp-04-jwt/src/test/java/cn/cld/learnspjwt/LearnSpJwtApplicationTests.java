package cn.cld.learnspjwt;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class LearnSpJwtApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> map = new HashMap<>();

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,20);
        Date time = instance.getTime();
        System.out.println(time);

        String sign = JWT.create()
                .withHeader(map)
                .withClaim("userId", 21)
                .withClaim("username", "cld")
                .withExpiresAt(instance.getTime()) //指定令牌的过期时间
                .sign(Algorithm.HMAC256("@#$%FASGY"));//签名

        System.out.println(sign);
        // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTk1NzM5NjMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiY2xkIn0.bdx8sGk9seKpsn3WBpEi7-Tv4E_sfU8KTNaFshSQv1I

        JWTVerifier jWTVerifier = JWT.require(Algorithm.HMAC256("@#$%FASGY")).build();
        DecodedJWT verify = jWTVerifier.verify(sign);

        System.out.println(verify.getClaim("userId").asInt());


    }

    // 令牌的验证
    @Test
    public void fun() {

    }

}
