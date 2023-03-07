package com.jj.stu.attendance.base.basic;

/**
 * 封装Api错误码
 */
public interface IErrorCode {
    /**
     * 获取状态码
     *
     * @return
     */
    int getCode();

    /**
     * 获取信息
     *
     * @return
     */
    String getMessage();
}
