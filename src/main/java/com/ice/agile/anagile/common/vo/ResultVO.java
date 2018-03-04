package com.ice.agile.anagile.common.vo;

import lombok.Data;

/**
 * Http请求返回统一外层对象
 * Cteated by wangpeng
 * 2018/3/1 16:40
 */
@Data
public class ResultVO<T> {

    /** 状态码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体数据. */
    private T data;

}
