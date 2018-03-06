package com.ice.agile.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/7 0:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MD5UtilTest {

    @Test
    public void MD5() {
    }

    @Test
    public void generate() {
        String md5 = MD5Util.generate("admin");
        System.out.println(md5);
    }

    @Test
    public void verify() {
    }
}