package com.jj.stu.attendance.admin.constants;

public enum LeavStatusEnum {
    REVIEWED(0, "待审核"),
    APPROVE(1, "待审批"),
    COMPLETED(2, "已完成"),
    CANCEL(3, "已取消");


    private final Integer code;
    private final String desc;

    LeavStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
