package com.heysong.config.handler;

import com.heysong.model.result.CodeEnum;
import com.heysong.model.result.R;
import com.heysong.util.JSONUtils;
import com.heysong.util.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 8912
 * @Date: 2025/2/27 3:21
 * @Version: v1.0.0
 * @Description:  访问拒绝处理器
 **/
@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result = R.builder().code(CodeEnum.FAIL.getCode()).msg("该用户没有权限！").build();

        String json = JSONUtils.beanToJson(result);
        log.info(json);
        ResponseUtils.writer(response, json);

    }
}