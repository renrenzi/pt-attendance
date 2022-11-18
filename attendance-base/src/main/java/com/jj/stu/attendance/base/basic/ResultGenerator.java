package com.jj.stu.attendance.base.basic;

import com.jj.stu.attendance.base.constants.HttpStatusEnum;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public ResultGenerator() {

    }
    public static <T> Result<T> getResultByMessage(String message) {
        Result<T> result = new Result<T>();
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> getResultByErrorCode(IErrorCode errorCode) {
        Result<T> result = new Result<T>();
        result.setData((T) errorCode);
        return result;
    }
    public static <T> Result<T> getResultByHttp(HttpStatusEnum constants, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(constants.getCode());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    /**
     * 自定义提示消息
     *
     * @param constants
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> getResultByHttp(HttpStatusEnum constants, T data) {
        Result<T> result = new Result<T>();
        result.setCode(constants.getCode());
        result.setMessage(constants.getContent());
        result.setData(data);
        return result;
    }

    /**
     * 自定义提示消息
     *
     * @param constants
     * @param <T>
     * @return
     */
    public static <T> Result<T> getResultByHttp(HttpStatusEnum constants) {
        Result<T> result = new Result<T>();
        result.setCode(constants.getCode());
        result.setMessage(constants.getContent());
        return result;
    }

    /**
     * 自定义提示消息
     *
     * @param constants
     * @param <T>
     * @return
     */
    public static <T> Result<T> getResultByMsg(HttpStatusEnum constants, String message) {
        Result<T> result = new Result<T>();
        result.setCode(constants.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> getResultByOk(T data) {
        Result<T> result = new Result<T>();
        result.setCode(HttpStatusEnum.OK.getCode());
        result.setMessage(HttpStatusEnum.OK.getContent());
        result.setData(data);
        return result;
    }
    public static <T> Result<T> getResultByError(T data) {
        Result<T> result = new Result<T>();
        result.setCode(HttpStatusEnum.BAD_GATEWAY.getCode());
        result.setMessage(HttpStatusEnum.BAD_GATEWAY.getContent());
        result.setData(data);
        return result;
    }
}
