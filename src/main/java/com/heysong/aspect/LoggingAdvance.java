package com.heysong.aspect;

import com.heysong.util.JSONUtils;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class LoggingAdvance {
    // 第一个切面：
    // 切点表达式：execution(* com.heysong.controller.*.*(..))
    // 目标方法：com.heysong.controller 包下所有类的所有方法，无论方法返回类型、参数情况如何。
    // 对应增强方法
    // 所以说可以理解为一个类型的增强类   对controller和mapper包下的所有的方法进行了增强
    // 同一个目标方法也就是同一个切点 有不同的通知（增强）  有连个切面 两个aop  那么他们的执行有没有顺序
    public static final String POINT_CUT_CONTROLLER = "execution(* com.heysong.controller.*.*(..))";
    public static final String POINT_CUT_DATA = "execution(* com.heysong.mapper.*.*(..))";



    @Around(POINT_CUT_CONTROLLER)
    public Object logAround(ProceedingJoinPoint point) {
        // 原有代码保持不变
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        ApiOperation apiOperation = method.getDeclaredAnnotation(ApiOperation.class);
        String operation = "";
        if (!ObjectUtils.isEmpty(apiOperation)) {
            operation = apiOperation.value();
        }
        Instant startTime = Instant.now();
        try {
            result = point.proceed(args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);

        Object[] processedArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof MultipartFile) {
                processedArgs[i] = "file";
            } else {
                processedArgs[i] = args[i];
            }
        }
        String argsJson = args.length == 0 ? "" : JSONUtils.beanToJson(processedArgs);

        log.info("方法耗时约{}ms，请求接口:{}，ip为{}，方法名为:{},入参:{},方法描述:{}",
                duration.toMillis(),
                requestURI,
                remoteAddr,
                methodName,
                argsJson,
                operation
        );
        return result;
    }

    // @Autowired
    // private SqlSessionFactory sqlSessionFactory;
    // @Pointcut(POINT_CUT_CONTROLLER)

    // @Around(POINT_CUT_DATA)
    // public Object dataLogAround(ProceedingJoinPoint point) throws Throwable {
    //     Signature signature = point.getSignature();
    //
    //     Class<?> mapperClass = point.getSignature().getDeclaringType();
    //     MethodSignature methodSignature = (MethodSignature) signature;
    //     Method method = methodSignature.getMethod();
    //     String methodName = method.getName();
    //     Parameter[] parameters = method.getParameters();
    //     List<String> parameterNames = new ArrayList<>();
    //     for (Parameter parameter : parameters) {
    //         parameterNames.add(parameter.getName());
    //     }
    //     String paraMeter = String.join(", ", parameterNames);
    //
    //     Instant startTime = Instant.now();
    //     Object result = null;
    //     String sql = "";
    //     try {
    //         // 获取 Mapper 方法的全限定名
    //         String mappedStatementId = mapperClass.getName() + "." + methodName;
    //         // 从 SqlSessionFactory 中获取 Configuration
    //         MappedStatement mappedStatement = sqlSessionFactory.getConfiguration().getMappedStatement(mappedStatementId);
    //         Object[] args = point.getArgs();
    //         BoundSql boundSql = mappedStatement.getBoundSql(args[0]);
    //         sql = boundSql.getSql();
    //         result = point.proceed(point.getArgs());
    //     } catch (Throwable e) {
    //         log.error(e.getMessage());
    //         throw new RuntimeException(e);
    //     }
    //     Instant endTime = Instant.now();
    //     Duration duration = Duration.between(startTime, endTime);
    //     log.info("Mapper: {}，dao 方法耗时约{}ms，sql 语句：{}, 方法名为:{}, 入参:{}",
    //             mapperClass.getName(),
    //             duration.toMillis(),
    //             sql,
    //             methodName,
    //             paraMeter
    //     );
    //     return result;
    // }
}