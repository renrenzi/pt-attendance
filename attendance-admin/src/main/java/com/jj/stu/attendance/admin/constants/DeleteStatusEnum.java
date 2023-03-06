package com.jj.stu.attendance.admin.constants;



/**
 * @author 任人子
 */
public enum DeleteStatusEnum {

    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除");

    private final int status;
    private final String note;

    DeleteStatusEnum(int status, String note) {
        this.status = status;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public int getStatus() {
        return status;
    }
}
