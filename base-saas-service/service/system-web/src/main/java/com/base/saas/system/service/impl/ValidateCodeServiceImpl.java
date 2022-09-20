package com.base.saas.system.service.impl;

import com.base.saas.common.constant.AppConstant;
import com.base.saas.system.model.ValidateCode;
import com.base.saas.system.service.ValidateCodeService;
import com.base.saas.util.ValidateCodeUtil;
import com.base.saas.util.redis.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ValidateCodeService 实现类
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取验证码信息
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    @Override
    public ValidateCode get() {
        ValidateCodeUtil.Validate randomCode = ValidateCodeUtil.getRandomCode();//直接调用静态方法，返回验证码对象
        if(randomCode == null){
            return null;
        }

        String value = randomCode.getValue().toLowerCase();
        String md5Key = DigestUtils.md5Hex(value);
        String redisKey = getRedisKey(md5Key);
        redisUtil.set(redisKey, value,5*60);   //5分钟过期
        String base64Str = randomCode.getBase64Str();
        return new ValidateCode(md5Key, ValidateCodeUtil.getBase64Image(base64Str));
    }

    /**
     * 获取redis key
     * @param md5Key value的md5加密值
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    public String getRedisKey(String md5Key){
        return AppConstant.VALIDATE_REDIS_KEY_PREFIX + md5Key;
    }

    /**
     * 验证
     * @param validateCode
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    public boolean check(ValidateCode validateCode){
        if(!redisUtil.hasKey(getRedisKey(validateCode.getKey()))){
            return false;
        }
        Object redisValue = redisUtil.get(getRedisKey(validateCode.getKey()));
        if(!redisValue.equals(validateCode.getCode().toLowerCase())){
            return false;
        }
        return true;
    }

}

