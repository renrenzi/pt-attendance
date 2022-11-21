package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
@TableName("s_user_role_resource_relation")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRoleResourceRelation implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "资源ID")
    private Integer resourceId;

    private static final long serialVersionUID = 1L;

}