package com.base.saas.util.redis;

/**
 * Title :
 * Description : redis key常量类
 * Create on : 2018年05月16日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public class RedisKeyConstants {
    /**
     * key的统一前缀
     */
    public static final String KEY_PREFIX = "ZW.FIN.";

    /**
     * 测试的key
     */
    public static final String TEST = "TEST";

    /**
     * 系统参数表
     */
    public static final String SYSTEM_PARA = "SYSTEM_PARA";

    /**
     * 菜单url
     */
    public static final String MENU_LIST = "MENU_LIST";

    /**
     * 字典itemcode
     */
    public static final String DICT_ITEM_CODE = "DICT_ITEM_CODE_";

    /**
     * 登录用户前缀
     */
    public static final String LOGIN_PREFIX = "LOGIN_PREFIX_";

    /**
     * 电信登录用户前缀
     */
    public static final String CT_LOGIN_PREFIX = "CT_LOGIN_PREFIX_";


    /**
     * 用户手机号和用户ID映射
     */
    public static final String PHONE_USERID_PAIR_PREFIX = "PHONE_USERID_PAIR_PREFIX_";

    /**
     * 地区-省
     */
    public static final String ADDR_PROVINCE_LIST = "ADDR_PROVINCE_LIST";

    /**
     * 地区-市
     */
    public static final String ADDR_CITY_LIST = "ADDR_CITY_LIST";

    /**
     * 地区-区域
     */
    public static final String ADDR_AREA_LIST = "ADDR_AREA_LIST";

    /**
     * 系统参数表,根据code和公司编号
     */
    public static final String SYS_PARA = "SYS_PARA_";

    /**
     * 系统当前语言版本
     */
    public static final String SYS_LANGUAGE = "SYS_LANGUAGE_";

    /**
     * 短信验证码
     */
    public static final String SMS_CODE = "SMS_CODE_";
    /**
     * 公司code
     */
    public static final String COMPANY_CODE = "COMPANY_CODE_";
}
