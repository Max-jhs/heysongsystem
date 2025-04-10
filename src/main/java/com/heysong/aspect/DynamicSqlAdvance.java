package com.heysong.aspect;

import com.heysong.domain.LoginUser;
import com.heysong.domain.TUser;
import com.heysong.model.constant.Constants;
import com.heysong.model.query.BaseQuery;
import com.heysong.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/3/16 21:58
 * @Version: v1.0.0
 * @Description:
 **/
@Component
@Aspect
public class DynamicSqlAdvance {

    // private static final String point = "@annotation(com.heysong.aspect.DynamicSql)";
    // 使用 @Pointcut 注解定义切入点
    @Pointcut(value = "@annotation(com.heysong.aspect.DynamicSql)")
    public void pointCut() {
        // 方法体为空，仅用于定义切入点
    }
    @Around(value ="pointCut()")
    // 在 @Around 通知中引用切入点方法
    public Object sqlAdvance(ProceedingJoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        DynamicSql annotation = methodSignature.getMethod().getAnnotation(DynamicSql.class);
        String idFeild = annotation.idColumn();
        String tableName = annotation.tableName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String jwt ="";
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            jwt = request.getHeader(Constants.TOKEN);
        }
        LoginUser user = JWTUtils.parseTokenUser(jwt);

        List<String> roleList = user.getRoleList();
        //不是管理员 就要拼id了 给basequery属性赋值
        if (!roleList.contains("admin")) {
            //activityQuery
            if (point.getArgs()[0] instanceof BaseQuery) {
                BaseQuery baseQuery = (BaseQuery) point.getArgs()[0];
                // select * from t_user  <where> and xx.id =  ""
                baseQuery.setDySql(" and " + tableName + "." + idFeild + "=" + user.getId());
            }
        }
        // basequery没有值就是全表查询
        Object proceed = null;
        try {
            proceed = point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return proceed;
    }
}