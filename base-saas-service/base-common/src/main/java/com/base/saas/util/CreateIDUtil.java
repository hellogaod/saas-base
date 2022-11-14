package com.base.saas.util;

import java.util.UUID;

/**
 * Title :
 * Description : id生成工具类
 */
public class CreateIDUtil {
    /**
     * @Author:
     * @Date: 2018/05/23 09:28
     * @Params:
     * @Description: 获取id
     * @return: String
     */
    public static String getId(){
        String id = UUID.randomUUID().toString();
        return id;
    }
}
