package com.ice.agile.anagile.service.system.impl;

import com.ice.agile.anagile.common.enums.CodeEnums;
import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.mapper.system.SysUserMapper;
import com.ice.agile.anagile.service.system.SysLoginService;
import com.ice.agile.annotation.AutomaticLog;
import com.ice.agile.execption.MyExecption;
import com.ice.agile.utils.HttpIpUtil;
import com.ice.agile.utils.MD5Util;
import com.ice.agile.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/6 10:36
 */
@Service
@AutomaticLog(name = "系统")
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 登录
     */
    @Override
    public ResultVO login(SysUser user, HttpServletRequest request) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new MyExecption(CodeEnums.PARAM_ISNOLL.getCode(), "请填入用户名或密码");
        }
        SysUser userFind = sysUserMapper.selectByUsername(user.getUsername());
        if (userFind == null) {
            throw new MyExecption(CodeEnums.RESULT_ERROR.getCode(), "用户名不存在");
        }
        if (!MD5Util.verify(user.getPassword(),userFind.getPassword())) {
            throw new MyExecption(CodeEnums.RESULT_ERROR.getCode(), "密码不正确");
        }
        //更新登录ip和登录时间
        userFind.setLoginIp(HttpIpUtil.getRequestIp(request));
        userFind.setLoginTime(new Timestamp(new Date().getTime()));
        //将用户信息存入session
        request.getSession().setAttribute("currentUser", userFind);

        return ResultUtil.success(userFind);
    }

    /**
     * 退出
     */
    @Override
    public ResultVO logout(SysUser currentUser, HttpServletRequest request) {
        currentUser.setLastLoginTime(currentUser.getLoginTime());
        currentUser.setLastLoginIp(currentUser.getLoginIp());
        sysUserMapper.updateById(currentUser);
        request.getSession().removeAttribute("currentUser");
        return ResultUtil.success("退出成功");
    }
}
