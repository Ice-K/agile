package com.ice.agile.anagile.service.system.impl;

import com.ice.agile.anagile.common.enums.CodeEnums;
import com.ice.agile.anagile.entity.system.SysActionLogger;
import com.ice.agile.anagile.mapper.system.SysActionLoggerMapper;
import com.ice.agile.anagile.service.system.SysActionLoggerService;
import com.ice.agile.execption.MyExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/5 16:45
 */
@Service
public class SysActionLoggerServiceImpl implements SysActionLoggerService {

    @Autowired
    private SysActionLoggerMapper sysActionLoggerMapper;

    @Override
    @Transactional
    public int insert(SysActionLogger logger) {
        if (logger == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "SysActionLogger：" + null);
        }
        return sysActionLoggerMapper.insert(logger);
    }

    @Override
    @Transactional
    public int del(Integer id) {
        if (id == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "id:" + null);
        }
        return sysActionLoggerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysActionLogger selectById(Integer id) {
        if (id == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "id:" + null);
        }
        return sysActionLoggerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysActionLogger> selectList(SysActionLogger logger) {
        return sysActionLoggerMapper.selectByQuery(logger);
    }
}
