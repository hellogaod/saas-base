package com.base.saas.util.validatecode;

import com.base.saas.util.redis.RedisUtil;

/**
 * @className: ValidateCodeOperationUtils
 * @author: 佛学徒
 * @date: 2022/10/20
 * @Description：图形验证码操作工具
 **/
public class ValidateCodeOperationUtils {

    /**
     * 获取redis key
     *
     * @param md5Key value的md5加密值
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    public static String getRedisKey(String md5Key) {
        return "validate_code_" + md5Key;
    }

    /**
     * 校验图形验证码
     *
     * @param redisUtil
     * @param key
     * @param code
     * @return 如果成功，则在redis中删除
     */
    public static boolean check(RedisUtil redisUtil, String key, String code) {
        // 校验验证码，从redis中获取
        Object redisValue = redisUtil.get(getRedisKey(key));
        if (redisValue == null && !redisValue.equals(code.toLowerCase())) {

            return false;
        }
        //如果校验成功后立马删除该图像code码
        redisUtil.del(getRedisKey(key));
        return true;
    }
}
