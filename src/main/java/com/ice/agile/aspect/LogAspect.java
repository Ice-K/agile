package com.ice.agile.aspect;

import com.ice.agile.anagile.common.constant.Constant;
import com.ice.agile.anagile.entity.system.SysUser;
import com.ice.agile.anagile.entity.system.SystemActionLogger;
import com.ice.agile.annotation.AutomaticLog;
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
 * AOP处理service层日志记录
 * Cteated by wangpeng
 * 2018/3/1 19:12
 */
@Aspect
@Component
public class LogAspect {




    /**
     *Service层添加操作切点
     */
    //@Pointcut(value = "@annotation(com.ice.agile.annotation.SystemServiceLog)")
    @Pointcut(value = "execution(public * com.ice.agile.anagile.service..impl.*ServiceImpl.save*(..))")
    private void serviceSaveLog() {}

    /**
     *Service层修改操作切点
     */
    @Pointcut(value = "execution(public * com.ice.agile.anagile.service..impl.*ServiceImpl.update*(..))")
    private void serviceUpdateLog() {}

    /**
     *Service层删除操作切点
     */
    @Pointcut(value = "execution(public * com.ice.agile.anagile.service..impl.*ServiceImpl.del*(..))")
    private void serviceDeleteLog() {}


    /**
     * 添加操作日志
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "serviceSaveLog()", argNames = "joinPoint,object", returning = "object")
    public void insertLog(JoinPoint joinPoint, Object object) {

        //判断参数
        if (joinPoint.getArgs() == null) { // 没有参数
            return;
        }

        Signature signature = joinPoint.getSignature();
        Class aClass = signature.getDeclaringType();// 获取切点方法所属的类
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();//获取切点方法签名

        //获取操作的模块
        String actionMenu = "";
        if (aClass.isAnnotationPresent(AutomaticLog.class)) {//判断类上是否有@AutoMaticLog注解
            AutomaticLog autoMaticLog = (AutomaticLog) aClass.getAnnotation(AutomaticLog.class);
            actionMenu = autoMaticLog.name();
        }

        //记录日志
        SysUser currentUser = AppUser.getCurrentUser();//获取当前操作的用户信息
        if (currentUser != null) {
            SystemActionLogger log = new SystemActionLogger();
            log.setUserId(currentUser.getId());//用户id
            log.setUsername(currentUser.getUsername());//用户名
            log.setIp(currentUser.getLoginIp());//用户登录ip
            log.setActionType(Constant.ActionType.ACTION_SAVE);//操作类型
            log.setActionMenu(actionMenu);//操作模块
            log.setActionDesc(getActionDesc(joinPoint.getArgs(),targetMethod.getName()));//操作描述
            log.setActionTime(new Timestamp(new Date().getTime()));
            log.setLoginType(Constant.LoginType.PC);//登录类型  0：pc  1：手机
            System.out.println(log.toString());
        }

    }


    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作描述
     * @param args  方法参数数组
     * @param mName    方法名
     * @return  操作描述
     */
    private String getActionDesc(Object[] args, String mName) {

        if (args == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        //sb格式 方法名[参数1，类型：String，值："abc"][参数2，........]
        sb.append(mName);
        String className;
        int index = 1;
        //1.便利参数对象
        for (Object info : args) {
            //2.获取对象类型
            className = info.getClass().getName();
            className = info.getClass().getName().substring(className.lastIndexOf(".")+1);
            sb.append("[参数").append(index).append("，类型：").append(className).append("，值：");
            //3.获取对象所有的方法
            Method[] methods = info.getClass().getDeclaredMethods();
            //4.遍历对象的方法，判断是不是get方法
            for (Method method : methods) {
                String methodName = method.getName();
                if (!methodName.contains("get")) {//不是get方法
                    continue;//执行下一次循环
                }
                //5.获取值
                Object reValue;
                try {
                    reValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }

                if (null != reValue) {
                    sb.append("(").append(methodName).append("：").append(reValue).append(")");
                }
            }
            sb.append("]");
            index++ ;
        }
        return sb.toString();
    }


}
