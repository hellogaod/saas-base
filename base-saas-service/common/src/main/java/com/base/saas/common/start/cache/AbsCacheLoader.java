package com.base.saas.common.start.cache;

import com.base.saas.common.start.IStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月21日
 * Copyright (C)
 *
 * @author department:研发部
 * username:zh-pc
 * @version 修改历史:
 * 修改人 修改日期 修改描述
 * -------------------------------------------<
 */
@Service
public abstract class AbsCacheLoader implements IStart, ICacheLoader {

    private static final Logger logger = LoggerFactory.getLogger(AbsCacheLoader.class);

    @Override
    public void start() {
        CacheLoaderAnno cacheLoadAnno = this.getClass().getAnnotation(CacheLoaderAnno.class);
        String desc = cacheLoadAnno.description();
        logger.info("开始加载:" + desc);
        this.clean();
        this.loading();
        logger.info("加载结束:" + desc);
    }

    @Override
    public void stop() {

    }
}
