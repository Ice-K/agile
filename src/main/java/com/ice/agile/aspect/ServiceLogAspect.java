package com.ice.agile.aspect;

import com.ice.agile.anagile.common.constant.Constant;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.entity.system.SystemActionLogger;
import com.ice.agile.annotation.SystemServiceLog;
import com.ice.agile.utils.AppUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * AOP处理
 * Cteated by wangpeng
 * 2018/3/1 19:12
 */
@Aspect
@Component
public class ServiceLogAspect {

    //记录Service层日志切点
    //@Pointcut(value = "execution(public * com.ice.agile.anagile.service..impl.*ServiceImpl.find*(..))")
    @Pointcut(value = "@annotation(com.ice.agile.annotation.SystemServiceLog)")
    public void ServiceLog() {}




    @AfterReturning(value = "ServiceLog()", argNames = "joinPoint,object", returning = "object")
    public void insertLog(JoinPoint joinPoint, Object object) {

        //判断参数
        if (joinPoint.getArgs() == null) { // 没有参数
            return;
        }

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        String actionMenu = "";
        Integer actionType = -1;
        if (targetMethod.isAnnotationPresent(SystemServiceLog.class)) {
            SystemServiceLog systemServiceLog = targetMethod.getAnnotation(SystemServiceLog.class);
            actionType = getActionType(systemServiceLog);
            actionMenu = systemServiceLog.name();
        }
        SysUser currentUser = AppUser.getCurrentUser();
        if (currentUser != null) {
            SystemActionLogger log = new SystemActionLogger();
            log.setUserId(currentUser.getId());//用户id
            log.setUsername(currentUser.getUsername());//用户名
            log.setIp(currentUser.getLoginIp());//用户登录ip
            log.setActionType(actionType);//操作类型
            log.setActionMenu(actionMenu);//操作模块
            log.setActionDesc("");//操作描述
            log.setActionTime(new Timestamp(new Date().getTime()));
            log.setLoginType(Constant.LoginType.PC);//登录类型  0：pc  1：手机
            System.out.println(log.toString());
        }

    }

    /**
     * 根据注解中的type类型判断执行的操作类型
     * @param systemServiceLog 注解类
     * @return                 返回操作类型代码
     */
    private Integer getActionType(SystemServiceLog systemServiceLog) {
        Integer actionType = null;
        if (Constant.ActionType.ACTION_SELECT.equals(systemServiceLog.type())) {//查询
            actionType = Constant.ActionType.ACTION_SELECT;
        }
        if (Constant.ActionType.ACTION_ADD.equals(systemServiceLog.type())) {//添加
            actionType = Constant.ActionType.ACTION_ADD;
        }
        if (Constant.ActionType.ACTION_DELETE.equals(systemServiceLog.type())) {//删除
            actionType = Constant.ActionType.ACTION_DELETE;
        }
        if (Constant.ActionType.ACTION_UPDATE.equals(systemServiceLog.type())) {//修改
            actionType = Constant.ActionType.ACTION_UPDATE;
        }
        if (Constant.ActionType.ACTION_LOGIN.equals(systemServiceLog.type())) {//登录
            actionType = Constant.ActionType.ACTION_LOGIN;
        }
        if (Constant.ActionType.ACTION_LOGOUT.equals(systemServiceLog.type())) {//退出
            actionType = Constant.ActionType.ACTION_LOGOUT;
        }
        return actionType;
    }


}
