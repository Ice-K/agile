package com.ice.agile.anagile.service.system;

import com.ice.agile.anagile.entity.system.SysActionLogger;

import java.util.List;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/5 16:43
 */
public interface SysActionLoggerService {

    int insert(SysActionLogger logger);

    int del(Integer id);

    SysActionLogger selectById(Integer id);

    List<SysActionLogger> selectList(SysActionLogger logger);
}
