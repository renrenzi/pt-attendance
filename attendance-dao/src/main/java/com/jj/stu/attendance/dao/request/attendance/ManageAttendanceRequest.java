package com.jj.stu.attendance.dao.request.attendance;

import com.jj.stu.attendance.dao.model.Attendance;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ManageAttendanceRequest {

    @NotNull
    private Attendance attendance;
}
