package com.ice.agile.anagile.entity.system;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Cteated by wangpeng
 * 2018/3/2 11:55
 */
@Data
public class SysActionLogger implements Serializable {
    private static final long serialVersionUID = 8458105046827771790L;
    /** 主键id. */
    private Integer id;

    /** 操作用户id. */
    private Integer userId;

    /** 操作用户登录名. */
    private String username;

    /** 用户登录Ip. */
    private String ip;

    /** 操作类型 0-登录  1-退出 2-添加 3-删除 4-修改 5-查询 . */
    private Integer actionType;

    /** 操作模块. */
    private String actionMenu;

    /** 操作描述. */
    private String actionDesc;

    /** 登录类型 0 pc登录  1手机登录. */
    private Integer loginType;

    /** 操作时间. */
    private Date actionTime;




}
