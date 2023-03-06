package com.jj.stu.attendance.meta.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 請假 request
 *
 * @author 张俊杰
 * @date 2023/02/07
 */
@Data
public class ManageLeaveRequest {

    private Integer id;

    /**
     * 学生Id
     */
    @NotNull
    private Integer studentId;

    private String info;
    private Date createDate;
    /**
     * 请假状态
     */
    private Boolean status;

    private String remark;
}
