package com.base.saas.util;

import java.util.UUID;

/**
 * Title :
 * Description : id生成工具类
 * Create on : 2018年05月23日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username: wangtao
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
public class CreateIDUtil {
    /**
     * @Author: wangtao
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
