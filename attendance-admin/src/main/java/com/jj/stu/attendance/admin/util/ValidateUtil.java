package com.jj.stu.attendance.admin.util;

import com.jj.stu.attendance.base.exception.ApiException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidateUtil {
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure().failFast(true)
            .buildValidatorFactory().getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            ConstraintViolation<T> item = constraintViolations.iterator().next();
            throw new ApiException(String.format("参数校验失败：[%s] %s", item.getPropertyPath(), item.getMessage()));
        }
    }
}
