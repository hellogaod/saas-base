package com.base.saas.manage.service.impl.system;

import com.base.saas.manage.mapper.enterprise.EntModuleMapper;
import com.base.saas.manage.mapper.system.SysMenuMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.enterprise.EntMenu;
import com.base.saas.manage.domain.entity.system.SysMenu;
import com.base.saas.manage.service.system.SysMenuService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.manage.mapper.enterprise.EntMenuMapper;
import com.base.saas.util.CreateIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Title :
 * Description : 系统菜单service，隶属于系统模块
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private EntMenuMapper entMenuMapper;
    @Resource
    private EntModuleMapper entModuleMapper;

    @Override
    public List<SysMenu> getAllMenuList(String moduleId, String parentId) throws Exception {
        return sysMenuMapper.getSysMenuList(moduleId, parentId, -1);
    }

    @Override
    public SysMenu getMenuByMenuId(String MenuId) {
        return sysMenuMapper.selectByPrimaryKey(MenuId);
    }

    @Override
    public List<SysMenu> getAllMenuTree(String moduleId, String parentId) {
        return sysMenuMapper.getAllMenuTreeByModuleId(moduleId, parentId);
    }

    @Override
    @Transactional
    public ReturnMap addMenu(SysMenu moduleDetail) throws Exception {
        //返回集合
        ReturnMap returnMap = new ReturnMap(0);
        //判断改菜单的URL是否已经存在
        if (!"".equals(moduleDetail.getUrl())) {
            int isUrlRepeat = sysMenuMapper.selectCountByMenuUrl(moduleDetail.getModuleId(), moduleDetail.getUrl());
            if (isUrlRepeat > 0) {//该菜单url已存在
                returnMap.setMsg("message.menu.url.existed");
                return returnMap;
            }
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        //创建人
        moduleDetail.setCreateUser(userInfo.getAccount());
        moduleDetail.setUpdateUser(userInfo.getAccount());

        String sysMenuCode = CreateIDUtil.getId();
        moduleDetail.setModuleId(sysMenuCode);
        moduleDetail.setCreateTime(new Date());
        moduleDetail.setUpdateTime(new Date());
        int result = sysMenuMapper.insertSelective(moduleDetail);
        if (result == 0) {
            returnMap.setMsg("message.system.sql.error");
            return returnMap;
        }

        //当前菜单所在模块如果被企业使用了（通过moduleId模块id判断），那么对通过companyCode对当前企业添加该菜单信息
        List<String> companyCodes = entModuleMapper.selectEntModleInfoByModuleId(moduleDetail.getModuleId());
        boolean respFlag = false;
        //存在关联企业
        if (companyCodes != null && companyCodes.size() > 0) {
            List<EntMenu> entMenuList = new ArrayList<>();
            for (String companyCode : companyCodes) {
                EntMenu entMenu = new EntMenu();
                entMenu.setId(CreateIDUtil.getId());
                entMenu.setMenuId(moduleDetail.getMenuId());
                entMenu.setModuleId(moduleDetail.getModuleId());
                entMenu.setCompanyCode(companyCode);
                entMenu.setCreateTime(new Date());
                entMenu.setCreateUser(moduleDetail.getCreateUser());
                entMenu.setRemark(moduleDetail.getRemark());
                entMenuList.add(entMenu);
            }
            //保存ent_menu数据
            if (entMenuList != null && entMenuList.size() > 0) {
                int emRow = entMenuMapper.addEntMenuList(entMenuList);
                if (emRow > 0) {
                    respFlag = true;
                }
            } else {
                respFlag = true;
            }
        } else {//模块未关联企业
            respFlag = true;
        }
        if (respFlag) {
            returnMap.setCode(1);
            returnMap.setMsg("message.system.save.success");
        } else {
            returnMap.setMsg("message.system.save.fail");
        }
        return returnMap;
    }

    @Override
    @Transactional
    public ReturnMap editMenu(SysMenu moduleDetail) throws Exception {
        //返回集合
        ReturnMap returnMap = new ReturnMap(0);
        //判断改菜单的URL是否已经存在
        if (!"".equals(moduleDetail.getUrl())) {
            int isUrlRepeat = sysMenuMapper.selectCountByMenuUrl(moduleDetail.getModuleId(), moduleDetail.getUrl());
            if (isUrlRepeat > 0) {//该菜单url已存在
                returnMap.setMsg("message.menu.url.existed");
                return returnMap;
            }
        }

        UserInfo userInfo = UserContextUtil.getUserInfo();
        //创建人
        moduleDetail.setUpdateUser(userInfo.getAccount());

        moduleDetail.setUpdateTime(new Date());
        int result = sysMenuMapper.updateByPrimaryKeySelective(moduleDetail);
        if (result == 0) {
            returnMap.setMsg("message.system.sql.error");
            return returnMap;
        }

        //修改ent_menu的关联数据
        EntMenu entMenu = new EntMenu();
        entMenu.setMenuId(moduleDetail.getMenuId());
        entMenu.setUpdateTime(new Date());
        entMenu.setUpdateUser(moduleDetail.getUpdateUser());
        entMenu.setRemark(moduleDetail.getRemark());
        entMenuMapper.updateEntMenuByEntMenu(entMenu);

        returnMap.setCode(1);
        returnMap.setMsg("message.system.update.success");
        return returnMap;
    }

    @Override
    @Transactional
    public ReturnMap updateMenuStatus(String menuId,int status) throws Exception {
        UserInfo userInfo = UserContextUtil.getUserInfo();

        //返回集合
        ReturnMap returnMap = new ReturnMap(0);

        //启用或暂停：本身及其下面的子菜单全部启动或暂停
        sysMenuMapper.updateSysMenuStatus(menuId,userInfo.getAccount(),status);

        //修改ent_menu关联的菜单信息
        EntMenu entMenu = new EntMenu();
        entMenu.setMenuId(menuId);
        entMenu.setUpdateTime(new Date());
        entMenu.setUpdateUser(userInfo.getAccount());

        //启用或暂停：本身及其下面的子菜单全部启动或暂停
        int row = entMenuMapper.updateEntMenuByEntMenu(entMenu);
        returnMap.setCode(1);
        returnMap.setMsg("message.system.update.success");
        return returnMap;
    }
}