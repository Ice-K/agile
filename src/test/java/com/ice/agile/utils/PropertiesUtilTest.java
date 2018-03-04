package com.ice.agile.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:
 * Cteated by wangpeng
 * 2018/3/4 9:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PropertiesUtilTest {

    @Test
    public void getProperty() {
        System.out.println( PropertiesUtil.getProperty("ceshi"));

    }
}