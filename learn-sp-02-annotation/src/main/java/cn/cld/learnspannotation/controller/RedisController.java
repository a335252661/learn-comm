package cn.cld.learnspannotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
@RestController
public class RedisController {

    @Autowired
    private  RedisTemplate redisTemplate;

    @RequestMapping("redis")
    public String fun() {
        String some = redisTemplate.opsForValue().get("n1").toString();
        return some;
    }
}
