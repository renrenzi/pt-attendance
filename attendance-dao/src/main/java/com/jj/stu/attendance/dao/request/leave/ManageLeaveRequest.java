package com.jj.stu.attendance.dao.request.leave;

import com.jj.stu.attendance.dao.model.Leave;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ManageLeaveRequest {

    @NotNull
    private Leave leave;
}
