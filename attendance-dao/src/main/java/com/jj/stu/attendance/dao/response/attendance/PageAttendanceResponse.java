package com.jj.stu.attendance.dao.response.attendance;

import com.jj.stu.attendance.dao.model.Attendance;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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
    private Integer totalSize;

    /**
     * 出席名单
     */
    private List<Attendance> attendanceList;
}
