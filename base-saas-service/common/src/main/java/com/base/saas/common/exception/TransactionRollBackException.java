package com.base.saas.common.exception;

import java.io.Serializable;

/**
 * Title :
 * Description :自定义事务回滚异常
 * Create on : 2018年05月15日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public class TransactionRollBackException extends RuntimeException implements Serializable {

    public TransactionRollBackException(String msg) {
        super(msg);
    }

    public TransactionRollBackException(Throwable throwable) {
        super(throwable);
    }

    public TransactionRollBackException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
