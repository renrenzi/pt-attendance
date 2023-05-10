package com.jj.stu.attendance.base.aop;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.StringConstants;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.service.RedisService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zhangjunjie
 * @description
 */
@Aspect
@Slf4j
@Component
public class UserInformationIntoAop {

    @Resource
    private RedisService redisService;

    @SneakyThrows
    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public Object around(ProceedingJoinPoint pjp) {
        try{
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            HandlerMethod handlerMethod = new HandlerMethod(pjp.getTarget(), methodSignature.getMethod());
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

            if (ArrayUtil.isNotEmpty(methodParameters)) {
                for (MethodParameter methodParameter : methodParameters) {
                    if (methodParameter.getParameterType().isAssignableFrom(StpUserDetail.class)) {
                        Object obj = pjp.getArgs()[methodParameter.getParameterIndex()];
                        if (obj instanceof StpUserDetail) {
                            StpUserDetail detail = (StpUserDetail) obj;
                            if (StrUtil.isBlankIfStr(detail.getUserId())) {
                                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                                String tokenValue = servletRequestAttributes.getRequest().getHeader("Authorization");
                                Integer loginId = Integer.valueOf((String) StpUtil.getLoginIdByToken(tokenValue));
                                StpUserDetail userDetail = (StpUserDetail)(redisService.hGet(StringConstants.USER_INFO_MAP_KET, StringConstants.USER_INFO + ":" + loginId));
                                if (Objects.isNull(userDetail)) {
                                    throw new ApiException("缓存中不存在改用户信息");
                                }
                                detail.setAdminId(userDetail.getAdminId());
                                detail.setUserId(userDetail.getUserId());
                                detail.setUsername(userDetail.getUsername());
                                detail.setNickName(userDetail.getUsername());
                                detail.setRoleName(userDetail.getRoleName());
                                log.info("detail -> {}", obj.toString());
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            throw new ApiException("用户未登录或 token 失效！");
        }

        Object result = pjp.proceed();
        return result;

    }
}
