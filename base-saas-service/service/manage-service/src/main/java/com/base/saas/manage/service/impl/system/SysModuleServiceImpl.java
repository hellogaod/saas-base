package com.base.saas.manage.service.impl.system;

import com.base.saas.manage.mapper.enterprise.EntModuleMapper;
import com.base.saas.manage.mapper.system.SysMenuMapper;
import com.base.saas.manage.mapper.system.SysModuleMapper;
import com.base.saas.manage.domain.model.ReturnMap;
import com.base.saas.manage.domain.entity.system.SysModule;
import com.base.saas.manage.domain.entity.system.SysMenu;
import com.base.saas.manage.service.system.SysModuleService;
import com.base.saas.userinfo.UserContextUtil;
import com.base.saas.userinfo.UserInfo;
import com.base.saas.util.CreateIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Title :
 * Description :系统模块service实现
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Autowired
    private SysMenuMapper sysModuleDetailMapper;

    @Autowired
    private EntModuleMapper entModuleMapper;

    @Override
    public List<SysModule> getSysModuleList(int status, String moduleName) throws Exception {
        return sysModuleMapper.findList(status, moduleName);
    }

    @Override
    @Transactional
    public ReturnMap saveSysModule(SysModule module) throws Exception {

        ReturnMap respMap = new ReturnMap(0);
        //判断moduleName是否存在重复
        List<SysModule> modules = sysModuleMapper.findList(-1, module.getModuleName());
        if (modules != null && modules.size() > 0) {
            respMap.setMsg("message.module.existed");
            return respMap;
        }


        //校验通过保存
        UserInfo userInfo = UserContextUtil.getUserInfo();

        Date now = new Date();
        module.setCreateTime(now);
        String moduleId = CreateIDUtil.getId();
        module.setModuleId(moduleId);
        module.setCreateUser(userInfo.getAccount());
        module.setUpdateUser(userInfo.getAccount());
        module.setUpdateTime(now);

        //添加系统模块的时候,默认给每个模块加一个系统管理的菜单
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(CreateIDUtil.getId());
        sysMenu.setModuleId(moduleId);
        sysMenu.setMenuName("系统管理");
        sysMenu.setUrl("");
        sysMenu.setSequence(1);
        sysMenu.setIcon("fa fa-cogs");
        sysMenu.setParentId("#");
        sysMenu.setCreateUser(module.getCreateUser());
        sysMenu.setCreateTime(now);
        sysMenu.setUpdateUser(module.getUpdateUser());
        sysMenu.setUpdateTime(now);
        sysMenu.setStatus((short) 1);
        sysMenu.setModuleId(moduleId);
        sysMenu.setRemark("系统管理,添加模块时，会默认生成一个系统菜单");
        sysMenu.setDesensitizeStatus((short) 0);

        sysModuleDetailMapper.insertSelective(sysMenu);

        int row = sysModuleMapper.insertSelective(module);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        } else {
            respMap.setMsg("message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public ReturnMap updateSysModule(SysModule module) throws Exception {
        ReturnMap respMap = new ReturnMap(0);

        //判断 moduleName是否重复
        List<SysModule> modules = sysModuleMapper.findList(-1, module.getModuleName());
        if (modules != null && modules.size() > 0) {
            respMap.setMsg("message.module.existed");
            return respMap;
        }

        //校验通过 保存
        Date now = new Date();
        module.setUpdateTime(now);
        int row = sysModuleMapper.updateByPrimaryKeySelective(module);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        } else {
            respMap.setMsg("message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public ReturnMap updateSysModuleStatus(SysModule module) throws Exception {
        ReturnMap respMap = new ReturnMap(0);

        if (module.getStatus() == 0) {
            //停用当前系统模块必须判断该系统模块是否关联企业
            List entModuleList = entModuleMapper.selectEntModleInfoByModuleId(module.getModuleId());
            if (entModuleList != null && entModuleList.size() > 0) {
                respMap.setMsg("message.module.hasCompany");
                return respMap;
            }

        }
        Date now = new Date();
        module.setUpdateTime(now);
        int row = sysModuleMapper.updateByPrimaryKeySelective(module);
        if (row > 0) {
            respMap.setCode(1);
            respMap.setMsg("message.system.save.success");
        } else {
            respMap.setMsg("message.system.save.fail");
        }
        return respMap;
    }

    @Override
    public SysModule getModuleInfo(String moduleId) throws Exception {
        return sysModuleMapper.selectByPrimaryKey(moduleId);
    }
}
