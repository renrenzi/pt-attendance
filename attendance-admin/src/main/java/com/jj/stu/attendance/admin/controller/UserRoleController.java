package com.jj.stu.attendance.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.UserResourceService;
import com.jj.stu.attendance.admin.service.UserRoleResourceRelationService;
import com.jj.stu.attendance.admin.service.UserRoleService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.base.util.DateUtils;
import com.jj.stu.attendance.dao.model.UserRole;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;
import com.jj.stu.attendance.meta.request.UserRoleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色管理Controller
 *
 * @author 任人子
 * @date 2022/5/10  - {TIME}
 */
@Api(tags = "userRoleController", description = "用户角色管理")
@RequestMapping("/user/role")
@RestController
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserResourceService userResourceService;
    @Resource
    private UserRoleResourceRelationService roleResourceRelationService;

    @ApiOperation("分页获取角色列表")
    @PostMapping("/pageRole")
    public Result<PageResult<UserRole>> pageRole(PageCondition condition, UserRole userRole) {
        if (condition == null || condition.getPageNum() == null || condition.getPageSize() == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, userRoleService.pageRole(condition, userRole));
    }

    @ApiOperation("添加角色")
    @PostMapping("/addRole")
    public Result addRole(UserRoleRequest userRoleRequest) {
        if (userRoleRequest == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        userRoleRequest.setCreateTime(DateUtils.getLocalCurrentTime())
                .setRoleStatus(1);
        userRoleService.save(userRoleRequest);
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation("添加角色并赋予对应资源")
    @PostMapping("/addRoleAndResource")
    @Transactional(rollbackFor = {Exception.class})
    public Result addRoleAndResource(UserRoleRequest userRoleRequest) {
        if (userRoleRequest == null || CollectionUtils.isEmpty(userRoleRequest.getResourceIds())) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        userRoleRequest.setCreateTime(DateUtils.getLocalCurrentTime())
                .setRoleStatus(1);

        if (!userRoleService.save(userRoleRequest)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        saveRoleResourceRelationList(userRoleRequest);
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    /**
     * 保存角色资源关系List
     *
     * @param userRoleRequest
     * @return
     */
    private void saveRoleResourceRelationList(UserRoleRequest userRoleRequest) {
        List<UserRoleResourceRelation> roleResourceRelationList = userRoleRequest.getResourceIds().stream()
                .map(id -> new UserRoleResourceRelation().setResourceId(id)
                        .setRoleId(userRoleRequest.getRoleId())).collect(Collectors.toList());
        roleResourceRelationService.deleteByList(roleResourceRelationList);
        roleResourceRelationService.saveBatch(roleResourceRelationList);
    }

    @ApiOperation("修改角色权限")
    @PostMapping("/editRole")
    @Transactional(rollbackFor = {Exception.class})
    public Result editRole(UserRoleRequest userRoleRequest) {
        if (userRoleRequest == null || userRoleRequest.getRoleId() == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        userRoleRequest.setCreateTime(DateUtils.getLocalCurrentTime());
        boolean save = userRoleService.updateById(userRoleRequest);
        if (!CollectionUtils.isEmpty(userRoleRequest.getResourceIds())) {
            saveRoleResourceRelationList(userRoleRequest);
        }
        if (!save) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation("批量删除角色")
    @PostMapping("/deleteRoles")
    public Result deleteRoles(@RequestParam List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        boolean save = userRoleService.removeByIds(ids);
        roleResourceRelationService.remove(new QueryWrapper<UserRoleResourceRelation>().lambda().eq(UserRoleResourceRelation::getRoleId, ids.get(0)));
        if (!save) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

}
