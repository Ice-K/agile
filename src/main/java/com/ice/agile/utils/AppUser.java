package com.ice.agile.utils;

import com.ice.agile.anagile.entity.system.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Cteated by wangpeng
 * 2018/3/2 16:07
 */
public class AppUser {

    public static SysUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user = null;
        if (request != null) {
            user = (SysUser) request.getSession().getAttribute("currentUser");
        }
        return user;
    }
}
