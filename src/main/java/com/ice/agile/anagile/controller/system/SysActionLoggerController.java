package com.ice.agile.anagile.controller.system;

import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysActionLogger;
import com.ice.agile.anagile.service.system.SysActionLoggerService;
import com.ice.agile.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/5 17:02
 */
@RestController
@RequestMapping(value = "/log")
public class SysActionLoggerController {

    @Autowired
    private SysActionLoggerService sysActionLoggerService;


    /**
     * 根据条件查询所有日志
     * @param logger 条件
     * @return 日志list
     */
    @PostMapping(value = "findList")
    public ResultVO findList(SysActionLogger logger) {
        return ResultUtil.success(sysActionLoggerService.selectList(logger));
    }


    /**
     * 根据id查询日志
     * @param id 日志id
     * @return 日志对象
     */
    @GetMapping(value = "findOne")
    public ResultVO findOne(Integer id) {
        return ResultUtil.success(sysActionLoggerService.selectById(id));
    }
}
