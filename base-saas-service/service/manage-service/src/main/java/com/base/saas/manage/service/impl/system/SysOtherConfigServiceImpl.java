package com.base.saas.manage.service.impl.system;

import com.base.saas.manage.mapper.system.SysOtherColumnsConfMapper;
import com.base.saas.manage.mapper.system.SysOtherConfigMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntOtherConfig;
import com.base.saas.manage.domain.entity.system.SysOtherColumnsConf;
import com.base.saas.manage.domain.entity.system.SysOtherConfig;
import com.base.saas.manage.service.system.SysOtherConfigService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntOtherConfigMapper;
import com.base.saas.util.CreateIDUtil;
import com.base.saas.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Title :
 * Description : 系统三方配置
 */

@Service
public class SysOtherConfigServiceImpl implements SysOtherConfigService {

    @Autowired
    private SysOtherConfigMapper otherConfigMapper;

    @Autowired
    private EntOtherConfigMapper entOtherConfigMapper;

    @Autowired
    private SysOtherColumnsConfMapper otherColumnsConfMapper;


    @Override
    public List<SysOtherConfig> getOtherConfigList(Integer status, Integer type, String otherName) throws Exception {

        List<SysOtherConfig> findList = otherConfigMapper.findList(status, type, otherName);


        return findList;
    }

    /**
     * 保存
     *
     * @param sysOtherConfig
     * @return
     */
    @Override
    @Transactional
    public ReturnMap saveOtherConfig(SysOtherConfig sysOtherConfig) throws Exception {
        ReturnMap respMap = new ReturnMap(0);

        //判断otherName是否重复
        List<SysOtherConfig> sysOtherConfigs =  otherConfigMapper.findList(-1, -1, sysOtherConfig.getOtherName());

        if (sysOtherConfigs != null && sysOtherConfigs.size() > 0) {
            respMap.setMsg("message.otherConfig.paraName.existed");
            return respMap;
        }

        //判断基础配置仅为一条
        if (sysOtherConfig.getType() == 0) {
            //判断基础配置是否已存在
            List<SysOtherConfig> list =  otherConfigMapper.findList(-1, 0, null);

            if (list != null && list.size() > 0) {
                respMap.setMsg("message.otherConfig.baseConfig.isOnlyOne");
                return respMap;
            }
        }

        //判断参数字段是否为空
        List<SysOtherColumnsConf> detailList = sysOtherConfig.getDetailList();
        if (detailList == null || detailList.size() < 1) {
            respMap.setMsg("message.otherConfig.paraName.not.null");
            return respMap;
        }

        //判断当前集合中是否有重复paraCode或paraName
        List<SysOtherColumnsConf> details = new ArrayList<>();
        boolean b = false;
        boolean c = false;
        for (SysOtherColumnsConf sysOtherColumnsConf : detailList) {
            b = details.stream()
                    .anyMatch(
                            u -> String.valueOf(u.getParaCode()).equals(String.valueOf(sysOtherColumnsConf.getParaCode()))
                    );

            c = details.stream()
                    .anyMatch(
                            u ->  String.valueOf(u.getParaName()).equals(String.valueOf(sysOtherColumnsConf.getParaName()))
                    );
            if (b) {
                break;
            }
            if (c) {
                break;
            }
            details.add(sysOtherColumnsConf);
        }
        if (b) {
            respMap.setMsg("message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        if (c) {
            respMap.setMsg("message.otherConfig.paraName.existed");
            return respMap;
        }

        //判断数据库的paraCode是否重复
        List<SysOtherColumnsConf> confs = otherColumnsConfMapper.findExitsWithParaCode(detailList);
        if (confs != null && confs.size() > 0) {
            respMap.setMsg("message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        //获取用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();

        //保存sys_other_config
        Date now = new Date();
        String id = CreateIDUtil.getId();
        sysOtherConfig.setOtherId(id);
        sysOtherConfig.setCreateTime(now);
        sysOtherConfig.setStatus((short) 1);
        sysOtherConfig.setCreateUser(userInfo.getAccount());
        sysOtherConfig.setUpdateTime(now);
        sysOtherConfig.setUpdateUser(userInfo.getAccount());
        int sr = otherConfigMapper.insertSelective(sysOtherConfig);
        if (sr > 0) {
            //保存sys_other_config成功
            //保存sys_other_columns_conf
            for (int i = 0; i < detailList.size(); i++) {
                SysOtherColumnsConf columnsConf = detailList.get(i);
                columnsConf.setId(CreateIDUtil.getId());
                columnsConf.setCreateTime(now);
                columnsConf.setUpdateTime(now);
                columnsConf.setUpdateUser(userInfo.getAccount());
                columnsConf.setCreateUser(userInfo.getAccount());
                columnsConf.setStatus((short) 1);
                columnsConf.setOtherId(id);
            }
            int dr = otherColumnsConfMapper.batchSave(detailList);
            if (dr > 0) {
                respMap.setCode(1);
                respMap.setMsg("message.system.save.success");
                return respMap;
            }
        }
        respMap.setMsg("message.system.save.fail");
        return respMap;
    }

    @Override
    public ReturnMap updateStatus(SysOtherConfig sysOtherConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();
        if (StringUtil.isEmpty(sysOtherConfig.getOtherId())) {
            respMap.setMsg("message.system.request.param.exception");
            return respMap;
        }

        //停用时判断 是否存在关联企业
        if (sysOtherConfig.getStatus() == 0) {
            List<EntOtherConfig> list = entOtherConfigMapper.findList(sysOtherConfig.getOtherId(), null);
            if (list != null && list.size() > 0) {
                respMap.setMsg("message.otherConfig.company.isconf");
                return respMap;
            }
        }
        UserInfo userInfo = UserContextUtil.getUserInfo();
        sysOtherConfig.setUpdateTime(new Date());
        sysOtherConfig.setUpdateUser(userInfo.getAccount());
        int row = otherConfigMapper.updateByPrimaryKeySelective(sysOtherConfig);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.update.success");
            return respMap;
        }
        respMap.setMsg("message.system.update.fail");
        return respMap;
    }

    /**
     * 修改参数配置
     *
     * @param sysOtherConfig
     * @return
     */
    @Override
    @Transactional
    public ReturnMap updateOtherConfig(SysOtherConfig sysOtherConfig) throws Exception {
        ReturnMap respMap = new ReturnMap();
        if (StringUtil.isEmpty(sysOtherConfig.getOtherId())) {
            respMap.setMsg("message.system.request.param.exception");
            return respMap;
        }

        //判断基础配置仅为一条
        if (sysOtherConfig.getType() == 0) {
            //判断基础配置是否已存在
            List<SysOtherConfig> list = otherConfigMapper.findList(-1, 0, null);
            if (list != null && list.size() > 0) {
                boolean flag = false;
                for (SysOtherConfig s : list) {
                    if (!s.getOtherId().equals(sysOtherConfig.getOtherId())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    respMap.setMsg("message.otherConfig.baseConfig.isOnlyOne");
                    return respMap;
                }
            }
        }

        //判断otherName是否重复
        List<SysOtherConfig> sysOtherConfigs = otherConfigMapper.findList(-1, -1, sysOtherConfig.getOtherName());
        if (sysOtherConfigs != null && sysOtherConfigs.size() > 0) {
            boolean flag = false;
            for (SysOtherConfig s : sysOtherConfigs) {
                if (!s.getOtherId().equals(sysOtherConfig.getOtherId())) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                respMap.setMsg("message.otherConfig.paraName.existed");
                return respMap;
            }
        }

        //判断参数配置字段是否为空
        if (sysOtherConfig.getDetailList() == null || sysOtherConfig.getDetailList().size() < 1) {
            respMap.setMsg("message.otherConfig.paraName.not.null");
            return respMap;
        }

        //判断当前集合中是否有重复paraCode
        List<SysOtherColumnsConf> details = new ArrayList<>();
        boolean b = false;
        for (SysOtherColumnsConf m : sysOtherConfig.getDetailList()) {
            b = details.stream().anyMatch(u -> String.valueOf(u.getParaCode()).equals(String.valueOf(m.getParaCode())));
            if (b) {
                break;
            }
            details.add(m);
        }
        if (b) {
            respMap.setMsg("message.otherConfig.paraCode.not.repeat");
            return respMap;
        }

        //判断数据库的paraCode是否重复----若把原配置字段删除新增一条同样的字段，也会报参数编码重复
        List<SysOtherColumnsConf> confs = otherColumnsConfMapper.findExitsWithParaCode(sysOtherConfig.getDetailList());
        for (SysOtherColumnsConf m : sysOtherConfig.getDetailList()) {
            if (b) {
                break;
            }
            for (SysOtherColumnsConf c : confs) {

                if (StringUtil.isEmpty(m.getId())) { //表示新增的三方配置参数的paracode编码和数据库重复
                    if (m.getParaCode().equals(c.getParaCode())) {
                        b = true;
                        break;
                    }
                } else {
                    //表示被修改的三方配置参数paracode和数据库其他paracode编码重复
                    if (!m.getId().equals(c.getId()) && m.getParaCode().equals(c.getParaCode())) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            respMap.setMsg("message.otherConfig.paraCode.not.repeat");
            return respMap;
        }
        //用户信息
        UserInfo userInfo = UserContextUtil.getUserInfo();
        String updateUser = String.valueOf(userInfo.getAccount());
        Date now = new Date();

        sysOtherConfig.setUpdateTime(now);
        sysOtherConfig.setUpdateUser(updateUser);
        //保存
        int sr = otherConfigMapper.updateByPrimaryKeySelective(sysOtherConfig);
        if (sr > 0) {
            //保存成功
            //删除数据库中有但准备修改的集合中没有
            //删除sys_other_column_conf
            int dr = otherColumnsConfMapper.batchDelNotInParamList(sysOtherConfig);

            //查询数据库原配置的字段
            List<SysOtherColumnsConf> confList = otherColumnsConfMapper.findList(sysOtherConfig.getOtherId(), null, null);
            //修改字段集
            List<SysOtherColumnsConf> updList = new ArrayList<>();
            //与现在准备修改的字段进行比较
            for (SysOtherColumnsConf c : confList) {
                //表示修改
                for (SysOtherColumnsConf detail : sysOtherConfig.getDetailList()) {
                    if (StringUtil.isNotEmpty(detail.getId()) && c.getId().equals(detail.getId())) {
                        c.setParaCode(detail.getParaCode());
                        c.setParaName(detail.getParaName());
                        c.setParaValue(detail.getParaValue());
                        c.setUpdateUser(updateUser);
                        c.setUpdateTime(now);
                        c.setRemark(detail.getRemark());
                        c.setSortting(detail.getSortting());
                        updList.add(c);
                        break;
                    }
                }
            }
            if (updList != null && updList.size() > 0) {
                //批量修改
                int ur = otherColumnsConfMapper.batchUpdate(updList);

            }
            //新增
            List<SysOtherColumnsConf> insList = new ArrayList<>();
            for (SysOtherColumnsConf detail : sysOtherConfig.getDetailList()) {
                if (StringUtil.isEmpty(detail.getId())) {
                    SysOtherColumnsConf conf = new SysOtherColumnsConf();
                    conf.setId(CreateIDUtil.getId());
                    conf.setOtherId(sysOtherConfig.getOtherId());
                    conf.setParaName(detail.getParaName());
                    conf.setParaValue(detail.getParaValue());
                    conf.setParaCode(detail.getParaCode());
                    conf.setSortting(detail.getSortting());
                    conf.setRemark(detail.getRemark());
                    conf.setCreateUser(updateUser);
                    conf.setCreateTime(now);
                    conf.setStatus((short) 1);
                    insList.add(conf);
                }
            }
            if (insList != null && insList.size() > 0) {
                int ir = otherColumnsConfMapper.batchSave(insList);
            }
            respMap.setCode(1);
            respMap.setMsg("message.system.update.success");
            return respMap;
        }
        respMap.setMsg("message.system.update.fail");
        return respMap;
    }

    @Override
    public SysOtherConfig getOtherConfInfo(String otherId) throws Exception {
        //查询sys_other_config
        SysOtherConfig sysOtherConfig = otherConfigMapper.selectByPrimaryKey(otherId);
        //查询sys_other_column_conf
        List<SysOtherColumnsConf> confList = otherColumnsConfMapper.findList(otherId, null, null);
        if (sysOtherConfig != null) {
            sysOtherConfig.setDetailList(confList);

            return sysOtherConfig;
        }
        return null;
    }


}