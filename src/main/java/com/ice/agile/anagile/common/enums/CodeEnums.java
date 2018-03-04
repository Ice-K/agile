package com.ice.agile.anagile.common.enums;

/**
 * Cteated by wangpeng
 * 2018/3/1 16:49
 */
public enum CodeEnums {

    /** 处理结果状态 */
    RESULT_SUCCESS(0,"成功"),
    RESULT_ERROR(1,"失败"),
    RESULT_SYS_ERROR(2,"系统异常"),
    PARAM_ISNOLL(3,"参数为空"),

    ;
    private final Integer code;

    private final String message;

    CodeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
