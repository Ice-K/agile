package com.ice.agile.anagile.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.ice.agile.anagile.common.constant.Constant;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.mapper.system.SysUserMapper;
import com.ice.agile.anagile.service.system.SysUserService;
import com.ice.agile.annotation.SystemServiceLog;
import com.ice.agile.execption.MyExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Cteated by wangpeng
 * 2018/2/27 10:16
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> queryList(SysUser user) {
        PageHelper.startPage(1,5);
        return sysUserMapper.queryByParams(user);
    }

    @Override
    @SystemServiceLog(type = 5,name = "用户管理")
    public SysUser findById(Integer id) {
        if (StringUtils.isEmpty(id)) {
            throw new MyExecption(3,"id不能为空");
        }
        return sysUserMapper.selectById(id);
    }

    @Override
    @Transactional
    public int add(SysUser user) {
        return sysUserMapper.insert(user);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return sysUserMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int update(SysUser user) {
        return sysUserMapper.updateById(user);
    }
}
