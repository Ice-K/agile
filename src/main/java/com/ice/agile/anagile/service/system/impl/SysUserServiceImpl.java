package com.ice.agile.anagile.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.ice.agile.anagile.common.enums.CodeEnums;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.mapper.system.SysUserMapper;
import com.ice.agile.anagile.service.system.SysUserService;
import com.ice.agile.annotation.AutomaticLog;
import com.ice.agile.execption.MyExecption;
import com.ice.agile.utils.AppUser;
import com.ice.agile.utils.MD5Util;
import com.ice.agile.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Cteated by wangpeng
 * 2018/2/27 10:16
 */
@Service
@AutomaticLog(name = "用户管理")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;




    @Override
    public List<SysUser> findList(SysUser user) {
        PageHelper.startPage(1,5);
        return sysUserMapper.selectByQuery(user);
    }

    @Override
    public SysUser findById(Integer id) {
        if (id == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(),"id不能为空");
        }
        SysUser user = sysUserMapper.selectById(id);
        if (user == null) {
            throw new MyExecption(CodeEnums.RESULT_ERROR.getCode(), "用户不存在");
        }
        return user;
    }

    @Override
    @Transactional
    public int save(SysUser user) {
        if (user == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "user = " + null);
        }
        //初始密码123456
        user.setPassword(MD5Util.generate(PropertiesUtil.getProperty("defaultPwd")));
        user.setCreateUser(AppUser.getCurrentUser().getUsername());
        int result = sysUserMapper.insert(user);
        if (result != 1) {
            throw new MyExecption(CodeEnums.RESULT_ERROR.getCode(), "添加失败");
        }
        return result;
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int update(SysUser user) {
        if (user == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "user = " + null);
        }
        int result = sysUserMapper.updateById(user);
        if (result == 0) {
            throw new MyExecption(CodeEnums.RESULT_ERROR.getCode(), "用户不存在");
        }
        return result;
    }
}
