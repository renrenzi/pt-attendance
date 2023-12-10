package com.jj.stu.attendance.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.UserResourceService;
import com.jj.stu.attendance.admin.service.UserRoleResourceRelationService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.base.util.DateUtils;
import com.jj.stu.attendance.dao.model.UserResource;
import com.jj.stu.attendance.dao.model.UserRoleResourceRelation;
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
 * 用户资源管理Controller
 *
 * @author 任人子
 */
@Api(tags = "用户资源管理")
@RequestMapping("/user/resource")
@RestController
public class UserResourceController {

    @Resource
    private UserResourceService userResourceService;
    @Resource
    private UserRoleResourceRelationService roleResourceRelationService;

    @ApiOperation("获取角色对应资源")
    @PostMapping("/getResourceByRoleId")
    @Transactional(rollbackFor = {Exception.class})
    public Result<List<UserResource>> getResourceByRoleId(Integer roleId) {
        if (roleId == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        List<Integer> resourceIds = roleResourceRelationService.list(new QueryWrapper<UserRoleResourceRelation>()
                        .lambda()
                        .eq(UserRoleResourceRelation::getRoleId, roleId))
                .stream()
                .map(UserRoleResourceRelation::getResourceId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resourceIds)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.INTERNAL_SERVER_ERROR);
        }
        List<UserResource> resourceList = userResourceService.listByIds(resourceIds);
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, resourceList);
    }

    @ApiOperation("分页获取资源列表")
    @PostMapping("/pageResource")
    public Result<PageResult<UserResource>> pageResource(PageCondition condition, UserResource userResource) {
        if (condition == null || condition.getPageNum() == null || condition.getPageSize() == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK, userResourceService.pageResource(condition, userResource));
    }

    @ApiOperation("添加资源")
    @PostMapping("/addResource")
    public Result addResource(UserResource userResource) {
        if (userResource == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        userResource.setCreateTime(DateUtils.getLocalCurrentTime());
        boolean save = userResourceService.save(userResource);
        if (!save) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    /**
     * @param userResource
     * @return
     */
    @ApiOperation("修改资源")
    @PostMapping("/editResource")
    public Result<String> editResource(UserResource userResource) {
        if (userResource == null || userResource.getId() == null) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        // 前端传参问题
        userResource.setCreateTime(null);
        boolean save = userResourceService.updateById(userResource);
        if (!save) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }

    @ApiOperation("批量删除资源")
    @PostMapping("/deleteResources")
    public Result<String> deleteResources(@RequestParam List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_REQUEST);
        }
        boolean save = userResourceService.removeByIds(ids);
        if (!save) {
            return ResultGenerator.getResultByHttp(HttpStatusEnum.BAD_GATEWAY);
        }
        userResourceService.initRoleResourceMap();
        return ResultGenerator.getResultByHttp(HttpStatusEnum.OK);
    }
}
