package com.heysong.config.handler;

import com.heysong.domain.LoginUser;
import com.heysong.mapper.TUserMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.result.CodeEnum;
import com.heysong.model.result.R;
import com.heysong.util.JSONUtils;
import com.heysong.util.JWTUtils;
import com.heysong.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 8912
 * @Date: 2025/2/27 2:49
 * @Version: v1.0.0
 * @Description: 登录成功处理器
 **/
@Component
public class MyAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private TUserMapper tUserMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 1. 获取认证用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取用户信息Json字符串
        String userJson = JSONUtils.beanToJson(loginUser);

        // 2. 根据 用户信息Json串 生成加密jwt
        String jwt = JWTUtils.createJwt(userJson);

        // 3. 根据是否记住我  保持登录时间不同

        redisTemplate.opsForValue().set(Constants.USER_TOKEN + loginUser.getId(),jwt);
        String rembMe = request.getParameter(Constants.REMB_ME);
        if (Boolean.parseBoolean(rembMe)) {
            redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 7, TimeUnit.DAYS);
        } else {
            redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 30, TimeUnit.MINUTES);
        }

        // 3. 封装返回统一结果中
        R result = R.builder()
                .code(CodeEnum.LOGIN_OK.getCode())
                .msg(CodeEnum.LOGIN_OK.getMsg())
                .data(jwt)
                .build();

        String json = JSONUtils.beanToJson(result);

        tUserMapper.updateLoginTime(loginUser.getId(), new Date());
        // 4. 响应
        ResponseUtils.writer(response, json);
    }
}