package com.base.saas.common.constant;

/**
 * <strong>Title : <br>
 * </strong> <strong>Description : </strong>@类注释说明写在此处@<br>
 * <strong>Create on : 2017年02月21日<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) Vbill Co.,Ltd.<br>
 * </strong>
 * <p>
 *
 * @author department:技术开发部 <br>
 * username:Administrator <br>
 * email: <br>
 * @version <strong>zw有限公司-运营平台</strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人 修改日期 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class AppConstant {

    /**
     * USER_INFO 用户信息
     */
    public static final String APP_USER_INFO = "APP_USER_INFO_";

    public static final String APP_SYS_ID_VALUE = "ZW_FIN";
    public static final String APP_SYS_ID_KEY = "APP_SYS_ID";

    public static final String ERROR_REQUESTMAPPING = "/comm/error";

    public static final String TIMEOUT_REQUESTMAPPING = "/comm/timeout";

    public static final String NOPERMISSION_REQUESTMAPPING = "/comm/noPermission";

    public static final String SUPER_ADMIN_USERNAME = "super_admin";

    public static final String ADMIN_USERNAME = "admin";
    //案件确认数据发送队列
    public static final String DATAINFO_CONFIRM_QE = "dataInfoExcel.confirm.progress.dev";

    //验证码redis key前缀
    public static final String VALIDATE_REDIS_KEY_PREFIX = "validate_code_";

    //默认公司编码
    public static final String DEFAULT_COMPANY_CODE = "XFJR201809130002";

    //规则引擎URL
    public static final String ENGINE_URL = "http://192.168.102.106:9094/datacenter/credit/application";

    //规则引擎UID
    public static final String ENGINE_UID = "liudehua";
    //规则引擎token
    public static final String ENGINE_TOKEN = "zw168cq";

    //默认分页
    public static final int DEFAULT_PAGE = 1;

    //默认分页
    public static final int DEFAULT_LIMIT = 20;

    //授信状态3无需还款
    public static final short CUSTOMER_AUTH_STATUS3 = 3;
    //1：已还清
    public static final int PLAN_STATUS1 = 1;
    //分期借款：borrow
    public static final String PAY_STATUS_BORROW = "borrow";
    //退款：refund
    public static final String PAY_STATUS_REFUND = "refund";
    //ing处理中
    public static final String INPUT_TYPE_ING = "ing";
    //in流入
    public static final String INPUT_TYPE_IN = "in";
    //out流出
    public static final String INPUT_TYPE_OUT = "out";
    //慧分期系统：HFQ
    public static final String SOURCE_HFQ = "HFQ";
    //退款接口状态：success
    public static final String RESPONSE_SUCCESS = "SUCCESS";
    //退款订单状态：success
    public static final String REFUND_SUCCESS = "REFUND_SUCCESS";
    //退款code：success
    public static final String REFUND_ERRORCODE = "020A58";

    //用户类型：app 用户
    public static final Integer  USER_TYPE_APP = 3;

    public static final int TEMP_PAGE_CODE = 233;
}

