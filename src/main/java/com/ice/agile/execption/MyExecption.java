package com.ice.agile.execption;

/**
 * 自定义异常类
 * Cteated by wangpeng
 * 2018/3/1 18:40
 */
public class MyExecption extends RuntimeException {

    private static final long serialVersionUID = 6574818691737395738L;


    private Integer code;

    public MyExecption(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
