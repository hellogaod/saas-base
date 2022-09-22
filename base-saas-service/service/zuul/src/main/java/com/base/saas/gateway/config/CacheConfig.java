package com.base.saas.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by qijigui on 2017/5/15.
 */

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.redis.expireTime}")
    private int expireTime;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager=new RedisCacheManager(redisTemplate());
        redisCacheManager.setDefaultExpiration(expireTime);
        return redisCacheManager;
    }
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        return new DefaultRedisTemplate(jedisConnectionFactory);
    }

}
