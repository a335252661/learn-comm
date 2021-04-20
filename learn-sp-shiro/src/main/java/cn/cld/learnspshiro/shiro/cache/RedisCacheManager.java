package cn.cld.learnspshiro.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author 程刘德
 * @version 1.0
 * @Description TODO
 * @date 2021/2/22
 */
//自定义的shiro 的redis 缓存管理器
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println("自定义的shiro 的redis 缓存管理器");
        System.out.println("String s :  " + cacheName );
        return new RedisCache<K, V>(cacheName);
    }
}
