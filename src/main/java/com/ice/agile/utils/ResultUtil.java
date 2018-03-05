package com.ice.agile.utils;

import com.ice.agile.anagile.common.enums.CodeEnums;
import com.ice.agile.anagile.common.vo.ResultVO;

/**
 * 返回结果装载类
 * Cteated by wangpeng
 * 2018/3/1 17:21
 */
public class ResultUtil {

    /**
     * 执行成功且有数据返回
     * @param object    传入数据
     * @return          结果vo
     */
    public static ResultVO success(Object object) {
        ResultVO vo = new ResultVO();
        vo.setCode(CodeEnums.RESULT_SUCCESS.getCode());
        vo.setMsg(CodeEnums.RESULT_SUCCESS.getMessage());
        vo.setData(object);
        return vo;
    }

    /**
     * 执行成功返回提示信息
     * @return 结果vo
     */
    public static ResultVO success(String msg) {
        ResultVO vo = new ResultVO();
        vo.setCode(CodeEnums.RESULT_SUCCESS.getCode());
        vo.setMsg(msg);
        vo.setData(null);
        return vo;
    }

    /**
     * 执行失败返回提示信息
     * @param code  状态码
     * @param msg   提示信息
     * @return      结果vo
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO vo = new ResultVO();
        vo.setCode(code);
        vo.setMsg(msg);
        vo.setData(null);
        return vo;
    }



}
