package com.ice.agile.anagile.service.system;

import com.ice.agile.anagile.entity.system.SysUser;

import java.util.List;

/**
 * Cteated by wangpeng
 * 2018/2/26 17:42
 */
public interface SysUserService {

    List<SysUser> queryList(SysUser user);

    SysUser findById(Integer id);

    int save(SysUser user);

    int delete(Integer id);

    int update(SysUser user);
}
