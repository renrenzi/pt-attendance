package com.jj.stu.attendance.admin.constants;

/**
 * 角色名称枚举
 * @author renrenzi
 */
public enum RoleNameEnum {

    STUDENT(7, "student"),
    TEACHER(8, "teacher"),
    MANAGER(9, "manager"),;

    private final Integer roleId;

    private final String roleName;

    RoleNameEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RoleNameEnum getRole(Integer roleId) {
        for (RoleNameEnum item : RoleNameEnum.values()) {
            if (item.getRoleId().equals(roleId)) {
                return item;
            }
        }
        return null;
    }
}
