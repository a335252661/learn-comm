package cn.cld.learnspshiro.shiro.cache;

import cn.cld.learnspshiro.utils.SpringContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/22
 */
//自定义redis缓存的实现
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    //    @Resource
//    private RedisTemplate redisTemplate;

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate= this.getRedisTemplate();
//        Object o = redisTemplate.opsForValue().get(k.toString());
        Object o = redisTemplate.opsForHash().get(cacheName, k.toString());
        return (V)o;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisTemplate redisTemplate= this.getRedisTemplate();
//        redisTemplate.opsForValue().set(k.toString(),v);
        redisTemplate.opsForHash().put(cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        Long delete = this.getRedisTemplate().opsForHash().delete(cacheName, k.toString());
        return (V)delete;
    }

    @Override
    public void clear() throws CacheException {
        this.getRedisTemplate().opsForHash().delete(cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(cacheName);
    }

    public RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate)SpringContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
