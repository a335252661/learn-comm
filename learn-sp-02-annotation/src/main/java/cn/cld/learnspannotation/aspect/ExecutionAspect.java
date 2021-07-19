package cn.cld.learnspannotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;

import java.lang.reflect.Field;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/7/12
 */
@Aspect
public class ExecutionAspect {

    @Pointcut("execution(public  * org.springframework.data.redis.connection.RedisConnectionFactory.getConnection(..))")
    private void getConnectionAspect() {
    }

    @Around("getConnectionAspect()")
    public Object wrapRedisConnection(ProceedingJoinPoint pjp) throws Throwable {
        RedisConnection connection = (RedisConnection)pjp.proceed();


        System.out.println("ExecutionAspect------------------------------------------");

        try {
            if (connection instanceof JedisConnection) {
//                JedisConnection jedisConnection = (JedisConnection)connection;
//                Jedis jedis = jedisConnection.getJedis();
//                ProxyFactoryBean factory = new ProxyFactoryBean();
//                factory.setProxyTargetClass(true);
//                factory.addAdvice(new JedisCommonInterceptor(this.tracing));
//                factory.setTarget(jedis);
//                Object object = factory.getObject();
//                Field field = jedisConnection.getClass().getDeclaredField("jedis");
//                field.setAccessible(true);
//                field.set(jedisConnection, object);
            }
        } catch (Exception var8) {

        }

        return connection;
    }

}
