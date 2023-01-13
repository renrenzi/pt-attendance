package com.jj.stu.attendance.dao.request;

import com.jj.stu.attendance.dao.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户角色请求Dto
 * @author 任人子
 * @date 2022/5/12  - {TIME}
 */
public class UserRoleRequestDto extends UserRole {

    /**
     * 资源Ids
     */
    @Getter
    @Setter
    private List<Integer> resourceIds;
}
