package com.ice.agile.system.service.impl;


import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.service.system.SysUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Cteated by wangpeng
 * 2018/2/27 10:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void queryList() {

    }

    @Test
    public void findById() {
        SysUser user = sysUserService.findById(1);
        Assert.assertNotNull(null,SysUser.class);
        System.out.println(user.getUsername());
    }

    @Test
    public void add() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}