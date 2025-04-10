package com.heysong.config.handler;

import com.heysong.domain.LoginUser;
import com.heysong.model.constant.Constants;
import com.heysong.model.result.CodeEnum;
import com.heysong.model.result.R;
import com.heysong.service.RedisService;
import com.heysong.util.JSONUtils;
import com.heysong.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: 8912
 * @Date: 2025/3/7 19:46
 * @Version: v1.0.0
 * @Description: 登出处理器
 **/
@Component
public class MyLogoutHandler implements LogoutHandler {
    @Resource
    private RedisService redisService;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 删除token
        LoginUser user = (LoginUser) authentication.getPrincipal();
        // LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean deleted = redisService.removeData(Constants.USER_TOKEN + user.getId());
        if (deleted) {
            R result = R.OK(CodeEnum.LOG_OUT);
            String json = JSONUtils.beanToJson(result);
            ResponseUtils.writer(response,json);
        }else{
            R result = R.FAIL();
            String json = JSONUtils.beanToJson(result);
            ResponseUtils.writer(response,json);
        }
    }
}