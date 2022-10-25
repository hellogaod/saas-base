package com.base.saas.manage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName SysWebLogRequest
 * @Description 日志查询参数
 * @Author coder_bao
 * @Date 2018/9/5 11:21
 */
@Data
@AllArgsConstructor
public class SysWebLogRequest {
    private String loginAccount;//登录账号
    private String companyCode;//公司编码
    private String method;//方法名
    private String terminalType;//终端(0-pc端，1-安卓，2-ios)
    private String operateType;//操作类型（0-登录日志，1-操作日志）
    private String status;//执行状态
    private String exceptionCode;//异常编码
    private String startTime;
    private String endTime;
    private Integer pageSize;
    private Integer pageIndex;

}
