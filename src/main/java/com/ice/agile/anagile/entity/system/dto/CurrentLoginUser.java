package com.ice.agile.anagile.entity.system.dto;

import com.ice.agile.anagile.entity.system.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * Cteated by wangpeng
 * 2018/3/2 16:18
 */
@Data
public class CurrentLoginUser extends SysUser implements Serializable {
    private static final long serialVersionUID = 5863838463661500254L;

}
