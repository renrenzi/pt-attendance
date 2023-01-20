package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.dao.model.Attendance;
import com.jj.stu.attendance.meta.dto.AttendanceDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 页面出席响应
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Data
@Accessors(chain = true)
public class PageAttendanceResponse {

    /**
     * 总大小
     */
    private Long totalSize;

    /**
     * 出席名单
     */
    private List<AttendanceDTO> attendanceList;
}
