package com.heysong.ex;

import com.heysong.model.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 8912
 * @Date: 2025/3/16 1:24
 * @Version: v1.0.0
 * @Description: 全局异常处理器
 **/
@RestControllerAdvice  //apo 拦截标注了所有RestController下的本包或者子包下所有方法
// @ControllerAdvice 拦截标注了所有Controller 下的本包或者子包下的所有方法
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return e.getMessage();
    }
    // // 异常的精确匹配  找不到， 找父类的异常处理器
    // @ExceptionHandler(DataAccessException.class)
    // public R exception(DataAccessException e) {
    //     e.printStackTrace();
    //     log.error(e.getMessage());
    //     return R.FAIL();
    // }

}