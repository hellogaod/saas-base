package com.base.saas.manager.service.impl;/**
 * Created by win7 on 2018/6/11.
 */

import com.base.saas.manager.mapper.EntMenuMapper;
import com.base.saas.manager.mapper.EntModuleMapper;
import com.base.saas.manager.mapper.SysModuleDetailMapper;
import com.base.saas.manager.model.EntMenu;
import com.base.saas.manager.model.SysModuleDetail;
import com.base.saas.manager.service.SysModuleDetailService;
import com.base.saas.util.CreateIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Title :
 * Description : @cm201805230001@
 * Create on : 2018年06月11日
 * Copyright (C) zw.FinTec
 *
 * @author department:研发部
 *         username:hanxiaoxue
 * @version 修改历史:
 *          修改人 修改日期 修改描述
 *          -------------------------------------------<
 */
@Service
public class SysModuleDetailServiceImpl implements SysModuleDetailService {

    @Resource
    private SysModuleDetailMapper moduleDetailMapper;
    @Resource
    private EntMenuMapper entMenuMapper;
    @Resource
    private EntModuleMapper entModuleMapper;

    @Override
    public List<Map> getModuleMenuListBySysCode(String sysCode) throws Exception{
        return moduleDetailMapper.getDetailMenuListBySysCode(sysCode);
    }


    @Override
    public List<SysModuleDetail> getAllMenuDetailList(SysModuleDetail moduleDetail) throws Exception{
        return moduleDetailMapper.getAllMenuDetailList(moduleDetail);
    }

    @Override
    public SysModuleDetail getMenuById(String id) {
        return moduleDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> getAllMenuTree(String sysCode) {
        return moduleDetailMapper.getAllMenuTreeBySysCode(sysCode);
    }

    @Override
    public List<SysModuleDetail> getOneMenu(String flag, String sysCode) {
        List list;
        if("1".equals(flag)){
            list = moduleDetailMapper.getOneMenu(sysCode);
        }else{
            list = moduleDetailMapper.getTwoMenu(sysCode);
        }

        return list;
    }

    @Override
    public Map checkMenuChildStatus(String id, String flag) {
        Map returnMap = new HashMap();
        SysModuleDetail moduleDetail = new SysModuleDetail();
        if("0".equals(flag)){//停用,去查看是否该菜单下有未停用的子菜单
            moduleDetail.setStatus((short) 1);
        }else{//启用,去查看是否该菜单下有未停用的子菜单
            moduleDetail.setStatus((short) 0);
            moduleDetail.setMenuType((byte) 2);
        }
        moduleDetail.setParentId(id);
        List<SysModuleDetail> moduleDetailList = moduleDetailMapper.getMenuByParentId(moduleDetail);
        if(moduleDetailList !=null && moduleDetailList.size()>0){
            returnMap.put("result",true);
        }else{
            returnMap.put("result",false);
        }
        return returnMap;
    }

    @Override
    public Map addMenu(SysModuleDetail moduleDetail)throws Exception{
        //返回集合
        Map returnMap = new HashMap();
        //判断改菜单的URL是否已经存在
        if(!"".equals(moduleDetail.getUrl())){
            List<SysModuleDetail> menuUrlList = moduleDetailMapper.selectMenuByMenuUrl(moduleDetail);
            if (menuUrlList != null && menuUrlList.size() > 0) {//该菜单名称已存在
                returnMap.put("flag", false);
                returnMap.put("msg", "message.menu.url.existed");
                return returnMap;
            }
        }
        String sysMenuCode = CreateIDUtil.getId();
        moduleDetail.setId(sysMenuCode);
        moduleDetail.setCreateTime(new Date());
        moduleDetail.setUpdateTime(new Date());
        int result = moduleDetailMapper.insertSelective(moduleDetail);
        if (result == 0) {
            returnMap.put("flag", false);
            returnMap.put("msg", "message.system.sql.error");
            return returnMap;
        }
        //往ent_menu新增加数据
        //查询sysCode下的所有企业company_code
        List<Map> companyList=entModuleMapper.findAllCompanyCodeBySysCode(moduleDetail.getSysCode());
        boolean respFlag = false;
        //存在关联企业
        if(companyList!=null &&companyList.size()>0) {
            List<EntMenu> entMenuList = new ArrayList<>();
            for (Map companyMap:companyList) {
                EntMenu entMenu = new EntMenu();
                entMenu.setId(CreateIDUtil.getId());
                entMenu.setSysCode(companyMap.get("sysCode").toString());
                entMenu.setCompanyCode(companyMap.get("companyCode").toString());
                entMenu.setUrl(moduleDetail.getUrl());
                entMenu.setCreateTime(new Date());
                entMenu.setAuthStatus(moduleDetail.getAuthStatus());
                entMenu.setDesensitizeStatus(moduleDetail.getDesensitizeStatus());
                entMenu.setIcon(moduleDetail.getIcon());
                entMenu.setMenuName(moduleDetail.getMenuName());
                entMenu.setMenuType(moduleDetail.getMenuType());
                entMenu.setParentId(moduleDetail.getParentId());
                entMenu.setParentName(moduleDetail.getParentName());
                entMenu.setSequence(moduleDetail.getSequence());
                entMenu.setRemark(moduleDetail.getRemark());
                entMenu.setStatus(moduleDetail.getStatus());
                entMenu.setSysMenuCode(sysMenuCode);
                entMenuList.add(entMenu);
            }
            //保存ent_menu数据
            if(entMenuList!=null&&entMenuList.size()>0) {
                int emRow = entMenuMapper.addEntMenuList(entMenuList);
                if (emRow > 0) {
                    respFlag = true;
                }
            }else{
                respFlag=true;
            }
        }else{//模块未关联企业
            respFlag = true;
        }
        if(respFlag){
            returnMap.put("flag", true);
            returnMap.put("msg", "message.system.save.success");
        }else{
            returnMap.put("flag", false);
            returnMap.put("msg", "message.system.save.fail");
        }
        return returnMap;
    }

    @Override
    public Map editMenu(SysModuleDetail moduleDetail)throws Exception{
        //返回集合
        Map returnMap = new HashMap();
        //判断改菜单的URL是否已经存在
        if(!"".equals(moduleDetail.getUrl())){
            List<SysModuleDetail> menuUrlList = moduleDetailMapper.selectMenuByMenuUrl(moduleDetail);
            if (menuUrlList != null && menuUrlList.size() > 0) {//该菜单名称已存在
                returnMap.put("flag", false);
                returnMap.put("msg", "message.menu.url.existed");
                return returnMap;
            }
        }
        moduleDetail.setUpdateTime(new Date());
        int result = moduleDetailMapper.updateByPrimaryKeySelective(moduleDetail);
        if (result == 0) {
            returnMap.put("flag", false);
            returnMap.put("msg", "message.system.sql.error");
            return returnMap;
        }
        //修改ent_menu的关联数据
        int row = entMenuMapper.updateEntMenuBySysMenu(moduleDetail);
        returnMap.put("flag", true);
        returnMap.put("msg", "message.system.update.success");
        return returnMap;
    }

    /**
     * 更新菜单状态,启用,停用
     * @param moduleDetail {id}菜单的id
     * @return
     */
    @Override
    public Map updateMenuStatus(SysModuleDetail moduleDetail) throws Exception{
        //返回集合
        Map returnMap = new HashMap();
        //停用
        if(moduleDetail.getStatus() == 0){
            //停用本身
            moduleDetailMapper.updateByPrimaryKeySelective(moduleDetail);
            //停用下面的子按钮
            moduleDetailMapper.updateStatusDisable(moduleDetail);
        }else{
            moduleDetailMapper.updateStatusEnable(moduleDetail);
        }
        //修改ent_menu关联的菜单信息
        int row = entMenuMapper.updateEntMenuBySysMenu(moduleDetail);
        returnMap.put("flag", true);
        returnMap.put("msg", "message.system.update.success");
        return returnMap;
    }
}