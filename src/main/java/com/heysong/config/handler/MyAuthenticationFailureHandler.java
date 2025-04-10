package com.heysong.config.handler;

import com.heysong.model.result.CodeEnum;
import com.heysong.model.result.R;
import com.heysong.util.JSONUtils;
import com.heysong.util.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 8912
 * @Date: 2025/2/27 3:16
 * @Version: v1.0.0
 * @Description: 登录失败处理器
 **/
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        R r = R.builder().code(CodeEnum.FAIL_ACCOUNT.getCode()).msg(CodeEnum.FAIL_ACCOUNT.getMsg()).build();
        String s = JSONUtils.beanToJson(r);
        ResponseUtils.writer(response,s);
    }
}