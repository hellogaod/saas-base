package com.base.saas.common.start;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月15日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
public interface IStart {

    void start() throws RuntimeException;

    void stop() throws RuntimeException;
}
