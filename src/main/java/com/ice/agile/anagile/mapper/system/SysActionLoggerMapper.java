package com.ice.agile.anagile.mapper.system;

import com.ice.agile.anagile.entity.system.SysActionLogger;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysActionLoggerMapper {

    /**
     * 根据条件查询所有日志记录
     * @param logger 日志对象
     * @return 日志集合
     */
    List<SysActionLogger> selectByQuery(SysActionLogger logger);

    /**
     * 删除
     * @param id 日志id
     * @return 删除个数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加
     * @param record 日志对象
     * @return 添加个数
     */
    int insert(SysActionLogger record);

    /**
     * 根据id查询日志
     * @param id 日志id
     * @return 日志对象
     */
    SysActionLogger selectByPrimaryKey(Integer id);

}