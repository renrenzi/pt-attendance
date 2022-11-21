package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;


import java.util.List;

/**
 * 角色资源关系Service
 * @author 任人子
 * @date 2022/5/12  - {TIME}
 */
public interface UserRoleResourceRelationService extends IService<UserRoleResourceRelation> {
    /**
     * 删除角色资源关系List
     * @param roleResourceRelationList
     * @return
     */
    int deleteByList(List<UserRoleResourceRelation> roleResourceRelationList);
}
