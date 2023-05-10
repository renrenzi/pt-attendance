package com.jj.stu.attendance.admin.constants;

import com.jj.stu.attendance.base.exception.ApiException;

/**
 * 角色名称枚举
 *
 * @author renrenzi
 */
public enum RoleNameEnum {

    STUDENT(7, "student", "学生"),
    TEACHER(8, "teacher", "教师"),
    MANAGER(9, "manager", "管理员"),
    ;

    private final Integer roleId;

    private final String roleName;

    private final String desc;
    RoleNameEnum(Integer roleId, String roleName, String desc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.desc = desc;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDesc() {
        return desc;
    }

    public static RoleNameEnum getRole(Integer roleId) {
        for (RoleNameEnum item : RoleNameEnum.values()) {
            if (item.getRoleId().equals(roleId)) {
                return item;
            }
        }
        throw new ApiException("该角色不存在" + roleId);
    }
}
