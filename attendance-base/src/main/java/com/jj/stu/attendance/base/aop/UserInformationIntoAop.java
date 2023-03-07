package com.jj.stu.attendance.base.aop;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.jj.stu.attendance.base.basic.StpUserDetail;
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

/**
 * @author zhangjunjie
 * @description
 */
@Aspect
@Slf4j
@Component
public class UserInformationIntoAop {
    @SneakyThrows
    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)")
    public Object around(ProceedingJoinPoint pjp){

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();

        HandlerMethod handlerMethod = new HandlerMethod(pjp.getTarget(),methodSignature.getMethod());
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

        if(ArrayUtil.isNotEmpty(methodParameters)){
            for (MethodParameter methodParameter : methodParameters) {
                if (methodParameter.getParameterType().isAssignableFrom(StpUserDetail.class)) {
                    Object obj = pjp.getArgs()[methodParameter.getParameterIndex()];
                    if(obj instanceof StpUserDetail){
                        StpUserDetail detail = (StpUserDetail)obj;
                        if (StrUtil.isBlankIfStr(detail.getUserId())) {
                            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                            String tokenValue = servletRequestAttributes.getRequest().getHeader("Authorization");
                            Integer loginId = Integer.valueOf((String) StpUtil.getLoginIdByToken(tokenValue));
                            detail.setUserId(1);
                            detail.setUsername("test");
                            detail.setNickName("test");
                            // todo 用户信息从redis中取
                            log.info("detail -> {}", obj.toString());
                        }

                    }


                }
            }
        }

        Object result = pjp.proceed();

        return result;

    }
}
