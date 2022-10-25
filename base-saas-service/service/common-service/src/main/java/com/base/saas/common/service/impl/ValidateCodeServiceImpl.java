package com.base.saas.common.service.impl;

import com.base.saas.common.model.ValidateCode;
import com.base.saas.common.service.ValidateCodeService;
import com.base.saas.util.validatecode.ValidateCodeOperationUtils;
import com.base.saas.util.validatecode.ValidateCodeUtil;
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
        String redisKey = ValidateCodeOperationUtils.getRedisKey(md5Key);
        redisUtil.set(redisKey, value,5*60);   //5分钟过期
        String base64Str = randomCode.getBase64Str();
        return new ValidateCode(md5Key, ValidateCodeUtil.getBase64Image(base64Str));
    }

    /**
     * 验证
     * @param validateCode
     * @author Mr.Zhang
     * @since 2020-04-16
     */
    public boolean check(ValidateCode validateCode){

        return ValidateCodeOperationUtils.check(redisUtil,validateCode.getKey(),validateCode.getCode());
    }

}

