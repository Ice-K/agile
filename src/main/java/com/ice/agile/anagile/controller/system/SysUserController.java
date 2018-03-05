package com.ice.agile.anagile.controller.system;


import com.ice.agile.anagile.common.enums.CodeEnums;
import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.service.system.SysUserService;
import com.ice.agile.utils.HttpIpUtil;
import com.ice.agile.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Cteated by wangpeng
 * 2018/2/27 14:28
 */
@RestController
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @param user 用户
     * @param request request
     * @return 登录结果
     */
    @PostMapping(value = "/login")
    public ResultVO login(SysUser user, HttpServletRequest request) {
        SysUser currentUser = sysUserService.findById(user.getId());
        if (currentUser == null) {
            return ResultUtil.error(CodeEnums.RESULT_ERROR.getCode(),"用户不存在");
        }

        currentUser.setLoginIp(HttpIpUtil.getRequestIp(request));
        request.getSession().setAttribute("currentUser", currentUser);
        return ResultUtil.success("登录成功");
    }

    /**
     * 退出
     * @param request request
     */
    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request){
        SysUser currentUser = (SysUser) request.getSession().getAttribute("currentUser");
        currentUser.setLastLoginIp(currentUser.getLoginIp());
        currentUser.setLostLoginTime(currentUser.getLoginTime());
        request.getSession().removeAttribute("currentUser");
        sysUserService.update(currentUser);
    }

    @GetMapping(value = "/getUser")
    public ResultVO getUser(Integer id) {
        return ResultUtil.success(sysUserService.findById(id));
    }


    @PostMapping(value = "/addUser")
    public int addUser(SysUser user) {
        return sysUserService.save(user);
    }

    @PostMapping(value = "/updateUser")
    public int updateUser(SysUser user) {
        return sysUserService.update(user);
    }

    @PostMapping(value = "/queryByParams")
    public List<SysUser> queryByParams(SysUser sysUser) {
        System.out.println(sysUser.getCreateTime());
        System.out.println(sysUser.getName());
        System.out.println(sysUser.getPhone());
        //sysUser.setPhone("12345678910");
        return sysUserService.findList(sysUser);
    }


}
