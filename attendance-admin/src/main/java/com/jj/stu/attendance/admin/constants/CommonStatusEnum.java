package com.jj.stu.attendance.admin.constants;

/**
 * 公共状态枚举
 *
 * @author LENOVO
 * @date 2023/01/20
 */
public enum CommonStatusEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用");
    private final Integer code;

    private final String status;

    CommonStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
