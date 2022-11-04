package com.base.saas.manage.start;

<<<<<<< HEAD
import com.base.saas.manage.mapper.enterprise.EntDictItemConfigMapper;
import com.base.saas.manage.mapper.enterprise.EntDictConfigMapper;
import com.base.saas.manage.domain.entity.enterprise.EntDictItemConfig;
import com.base.saas.manage.domain.entity.enterprise.EntDictConfig;
=======
import com.base.saas.common.start.StartAnno;
import com.base.saas.common.start.cache.AbsCacheLoader;
import com.base.saas.common.start.cache.CacheLoaderAnno;
import com.base.saas.manage.mapper.enterprise.EntDictItemConfigMapper;
import com.base.saas.manage.mapper.enterprise.EntDictConfigMapper;
import com.base.saas.manage.model.enterprise.EntDictItemConfig;
import com.base.saas.manage.model.enterprise.EntDictConfig;
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
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
 * Description : 字典存放到redis中,并且刷新：清除，重新添加
 */
@Component
<<<<<<< HEAD
public class EntDictCacheLoader implements ApplicationRunner, Ordered {
    private static Logger log = LoggerFactory.getLogger(EntDictCacheLoader.class);

=======
@StartAnno(description = "加载字典至Redis")
@CacheLoaderAnno(description = "加载字典至Redis")
public class EntDictCacheLoader extends AbsCacheLoader implements ApplicationRunner, Ordered {
    private static Logger log = LoggerFactory.getLogger(EntDictCacheLoader.class);
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
    @Autowired
    private EntDictConfigMapper dictItemMapper;
    @Autowired
    private EntDictItemConfigMapper dictDetailMapper;

<<<<<<< HEAD
    public void loading() {
        log.info("开始加载字典至Redis");
        //查询字典大类
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(1, null, null, null);

        //查询字典明细
        List<EntDictItemConfig> entDictItemConfigList = dictDetailMapper.findList(1, null, null, null, null);
=======
    @Override
    public void loading() {
        log.info("开始加载字典至Redis");
        //查询字典大类
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(1,null,null,null);

        //查询字典明细
        List<EntDictItemConfig> entDictItemConfigList = dictDetailMapper.findList(1,null,null,null,null);
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d

        for (EntDictConfig item : entDictConfigList) {
            List<Map> dictdetails = new ArrayList<>();
            for (EntDictItemConfig detail : entDictItemConfigList) {
                if (detail.getItemCode().equals(item.getItemCode())) {
                    Map dMap = new HashMap();
                    dMap.put("dictCode", detail.getDetailCode());
                    dMap.put("dictName", detail.getDetailName());
                    dMap.put("itemCode", detail.getItemCode());
                    dictdetails.add(dMap);
                }
            }
            RedisUtil.set(RedisKeyConstants.DICT_ITEM_CODE + item.getCompanyCode() + "_" + item.getItemCode(), dictdetails);
        }
        log.info("结束加载字典至Redis");
    }

<<<<<<< HEAD
=======
    @Override
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
    public void refresh() {
        clean();
        loading();
    }

<<<<<<< HEAD
    public void clean() {
        //查询字典大类
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(1, null, null, null);
=======
    @Override
    public void clean() {
        //查询字典大类
        List<EntDictConfig> entDictConfigList = dictItemMapper.findList(1,null,null,null);
>>>>>>> eb9a8c64842da76f204da857145ba23ff1c1240d
        for (EntDictConfig item : entDictConfigList) {
            RedisUtil.del(RedisKeyConstants.DICT_ITEM_CODE + item.getCompanyCode() + "_" + item.getItemCode());
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