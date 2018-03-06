package com.ice.agile.anagile.controller.system;

import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.service.system.SysLoginService;
import com.ice.agile.utils.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/6 9:47
 */
@RestController
@RequestMapping(value = "/system")

public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;


    /**
     * 登录
     * @param user 登录对象
     * @param request 请求
     * @return 登录结果
     */
    @PostMapping(value = "/login")
    public ResultVO login(SysUser user, HttpServletRequest request) {
        return sysLoginService.login(user, request);
    }


    @GetMapping(value = "/logout")
    public ResultVO logout(HttpServletRequest request) {
        return sysLoginService.logout(AppUser.getCurrentUser(), request);
    }

}
