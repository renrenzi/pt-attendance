package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@TableName("s_user_role_relation")
@Data
@Accessors(chain = true)
public class UserRoleRelation implements Serializable {
    @ApiModelProperty(value = "角色用户关系id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer adminId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;


}