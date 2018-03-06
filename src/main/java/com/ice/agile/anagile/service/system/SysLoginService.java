package com.ice.agile.anagile.service.system;

import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/6 10:34
 */
public interface SysLoginService {

    ResultVO login(SysUser user, HttpServletRequest request);

    ResultVO logout(SysUser currentUser, HttpServletRequest request);
}
