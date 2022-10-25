package com.base.saas.manage.start;/**
 * Created by win7 on 2018/5/29.
 */

import com.base.saas.common.start.StartAnno;
import com.base.saas.common.start.cache.AbsCacheLoader;
import com.base.saas.common.start.cache.CacheLoaderAnno;
import com.base.saas.manage.mapper.DictDetailMapper;
import com.base.saas.manage.mapper.DictItemMapper;
import com.base.saas.manage.model.DictDetail;
import com.base.saas.manage.model.DictItem;
import com.base.saas.util.redis.RedisKeyConstants;
import com.base.saas.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title :
 * Description : @类注释说明写在此处@
 * Create on : 2018年05月29日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Component
@StartAnno(description = "加载字典至Redis")
@CacheLoaderAnno(description = "加载字典至Redis")
public class DictCacheLoader extends AbsCacheLoader implements ApplicationRunner,Ordered {
    private static Logger log = LoggerFactory.getLogger(DictCacheLoader.class);
    @Autowired
    private DictItemMapper dictItemMapper;
    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public void loading() {
        log.info("开始加载字典至Redis");
        //查询字典大类
        Map param = new HashMap();
        param.put("status",1);
        List<DictItem> dictItemList = dictItemMapper.findList(param);
        Map map = new HashMap();
        map.put("status",1);
        List<DictDetail> dictDetailList = dictDetailMapper.findList(map);

        for (DictItem item:dictItemList) {
            List<Map> dictdetails = new ArrayList<>();
            for (DictDetail detail:dictDetailList) {
                if(detail.getItemCode().equals(item.getItemCode())){
                    Map dMap = new HashMap();
                    dMap.put("dictCode",detail.getDetailCode());
                    dMap.put("dictName",detail.getDetailName());
                    dMap.put("itemCode",detail.getItemCode());
                    dictdetails.add(dMap);
                }
            }
            RedisUtil.set(RedisKeyConstants.DICT_ITEM_CODE+item.getCompanyCode()+"_"+item.getItemCode(), dictdetails);
        }
        log.info("结束加载字典至Redis");
    }

    @Override
    public void refresh() {
        clean();
        loading();
    }

    @Override
    public void clean() {
        //查询字典大类
        Map param = new HashMap();
        param.put("status",1);
        List<DictItem> dictItemList = dictItemMapper.findList(param);
        for (DictItem item:dictItemList) {
            RedisUtil.del(RedisKeyConstants.DICT_ITEM_CODE+item.getCompanyCode()+"_"+item.getItemCode());
        }
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        loading();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}