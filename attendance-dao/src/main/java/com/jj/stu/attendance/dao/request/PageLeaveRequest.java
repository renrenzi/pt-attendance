package com.jj.stu.attendance.dao.request;

import com.jj.stu.attendance.dao.model.Leave;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页面请求离开
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
public class PageLeaveRequest {

    /**
     * 页面num
     */
    @NotNull
    private Integer pageNum;

    /**
     * 页面大小
     */
    @NotNull
    private Integer pageSize;

    /**
     * 离开
     */
    private Leave leave;
}
