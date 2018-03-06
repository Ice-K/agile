package com.ice.agile.anagile.controller.system;


import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.service.system.SysUserService;
import com.ice.agile.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 根据id获取用户
     * @param id 用户id
     * @return 用户对象
     */
    @GetMapping(value = "/getUser")
    public ResultVO getUser(Integer id) {
        return ResultUtil.success(sysUserService.findById(id));
    }


    /**
     * 添加用户
     * @param user 用户对象
     * @return 返回结果
     */
    @PostMapping(value = "/addUser")
    public ResultVO addUser(SysUser user) {
       sysUserService.save(user);
       return ResultUtil.success("添加成功");
    }

    /**
     * 修改用户
     * @param user 参数对象
     * @return 修改结果
     */
    @PostMapping(value = "/updateUser")
    public ResultVO updateUser(SysUser user) {
        sysUserService.update(user);
        return ResultUtil.success("修改成功");
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
