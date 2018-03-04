package com.ice.agile.anagile.entity.system;

import com.ice.agile.anagile.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Cteated by wangpeng
 * 2018/2/26 15:12
 */
@Data
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2214195686997295115L;

    private Integer id;         //主键id
    private Integer pid;        //上级id
    private String username;    //登录名
    private String password;    //密码
    private String name;        //真实姓名
    private String phone;       //手机号码
    private Integer deptmentId; //部门id
    private String lastLoginIp; //上次登录ip
    private String loginIp;     //当前登录ip
    private Date lostLoginTime; //上次登录时间
    private Date loginTime;     //当前登录时间
    private Integer isLock = 0; //是否锁定：0正常 1锁定

}
