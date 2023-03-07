package com.jj.stu.attendance.base.basic;

/**
 * 错误码结果封
 */
public class ResultCode implements IErrorCode {

    private final Result result;

    public ResultCode(Result result) {
        this.result = result;
    }

    @Override
    public int getCode() {
        return result.getCode();
    }

    @Override
    public String getMessage() {
        return result.getMessage();
    }
}
