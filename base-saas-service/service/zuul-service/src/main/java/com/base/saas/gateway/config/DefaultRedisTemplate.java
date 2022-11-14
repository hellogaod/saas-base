package com.base.saas.gateway.config;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class DefaultRedisTemplate extends RedisTemplate<Object, Object> {
    public DefaultRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        setConnectionFactory(jedisConnectionFactory);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        setKeySerializer(stringRedisSerializer);
//        setHashKeySerializer(stringRedisSerializer);
    }

}
