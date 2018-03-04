package com.ice.agile.anagile.mapper.system;

import com.ice.agile.anagile.entity.system.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    int insert(SysUser user);

    SysUser selectById(Integer id);

    List<SysUser> queryByParams(SysUser user);

    int updateById(SysUser user);

    int deleteById(Integer id);
}