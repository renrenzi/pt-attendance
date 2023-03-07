package com.jj.stu.attendance.base.exception;


import com.jj.stu.attendance.base.basic.IErrorCode;

/**
 * 自定义Api异常处理
 *
 * @author 任人子
 * @date 2022/5/12  - {TIME}
 */
public class ApiException extends RuntimeException {
    private IErrorCode iErrorCode;

    public ApiException(IErrorCode iErrorCode) {
        super(iErrorCode.getMessage());
        this.iErrorCode = iErrorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return iErrorCode;
    }
}
