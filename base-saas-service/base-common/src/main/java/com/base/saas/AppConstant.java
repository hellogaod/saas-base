package com.base.saas;

/**
 * <strong>Title : <br>
 * </strong> <strong>Description :
 */
public class AppConstant {

    public static final String API_PRE = "saas-";

    public static final String ZUUL_NAME = API_PRE + "zuul-gateway";
    public static final String MANAGE_NAME = API_PRE + "manage-service";
    public static final String COMMON_NAME = API_PRE + "common-service";

    //管理端登录（企业登录和系统登录）api包含字符串
    public static final String MANAGE_LOGIN_API_CONTAIN = "doLogin";
    //添加日志接口
    public static final String MANAGE_ADDLOG_API = MANAGE_NAME + "/api/entLog/addLog";


    /**
     * USER_INFO 用户信息
     */
    public static final String APP_USER_INFO = "SAAS_USER_INFO_";

}

