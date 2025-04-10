package com.heysong.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 8912
 * @Date: 2025/3/16 21:48
 * @Version: v1.0.0
 * @Description:
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSql {

    public String tableName () default "";

    public String idColumn() default "";

}
