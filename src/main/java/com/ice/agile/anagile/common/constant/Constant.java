package com.ice.agile.anagile.common.constant;

/**
 * Cteated by wangpeng
 * 2018/3/2 14:53
 */
public class Constant {

    /**
     * 操作类型标记
     */
    public static class ActionType {
        public static final Integer ACTION_LOGIN = 0;   //登录
        public static final Integer ACTION_LOGOUT = 1;  //退出
        public static final Integer ACTION_SAVE = 2;    //添加
        public static final Integer ACTION_DELETE = 3;  //删除
        public static final Integer ACTION_UPDATE = 4;  //修改
        public static final Integer ACTION_SELECT = 5;  //查询
    }

    /**
     * 登录类型标记
     */
    public static class LoginType {
        public static final Integer PC = 0;        //pc端登录
        public static final Integer PHONE = 1;     //手机端登录
    }


}
