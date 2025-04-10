package com.heysong.config.filter;

import com.heysong.domain.LoginUser;
import com.heysong.model.constant.Constants;
import com.heysong.model.result.CodeEnum;
import com.heysong.model.result.R;
import com.heysong.util.JSONUtils;
import com.heysong.util.JWTUtils;
import com.heysong.util.ResponseUtils;
import io.netty.util.internal.StringUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 8912
 * @Date: 2025/2/27 3:23
 * @Version: v1.0.0
 * @Description: token拦截器 要在security过滤器链之前执行
 **/
@Component
public class JwtVerityFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 前提不是登录请求下才检验token  登录请求直接放行
        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith(Constants.LOGIN_URI)) {
            if (requestURI.startsWith("/api/customer/exportExcel")||requestURI.startsWith("/api/customer/exportSelectedExcel")) {
                filterChain.doFilter(request, response);
            }
            //1、获取请求头中的token
            String token = request.getHeader(Constants.TOKEN);
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter(Constants.TOKEN);
            }
            //2、没有
            if (!StringUtils.hasText(token)) {
                R result = R.builder().code(CodeEnum.TOKEN_NULL.getCode()).msg(CodeEnum.TOKEN_NULL.getMsg()).build();
                String json = JSONUtils.beanToJson(result);
                ResponseUtils.writer(response,json);
                return;
            }
            //3、有 检验token合法性
            if (!JWTUtils.checkToken(token)) {
                R result = R.builder().code(CodeEnum.TOKEN_INVALID.getCode()).msg(CodeEnum.TOKEN_INVALID.getMsg()).build();
                String json = JSONUtils.beanToJson(result);
                ResponseUtils.writer(response,json);
                return;
            }
            
            //4、合法  去到redis中对比
            String userToken = JWTUtils.parseToken(token);
            LoginUser loginUser = JSONUtils.jsonToBean(userToken, LoginUser.class);
            String jwt = (String) redisTemplate.opsForValue().get(Constants.USER_TOKEN + loginUser.getId());
            //比对 redis中没有
            if (StringUtils.isEmpty(jwt)) {
                R result = R.builder().code(CodeEnum.TOKEN_NOT_EXIST.getCode()).msg(CodeEnum.TOKEN_NOT_EXIST.getMsg()).build();
                String json = JSONUtils.beanToJson(result);
                ResponseUtils.writer(response,json);
                return;
            }
            if (!jwt.equals(token)) {
                R result = R.builder().code(CodeEnum.TOKEN_NOT_EQUALS.getCode()).msg(CodeEnum.TOKEN_NOT_EQUALS.getMsg()).build();
                String json = JSONUtils.beanToJson(result);
                ResponseUtils.writer(response,json);
                return;
            }

            // 更新到SpringSecurity全局上下文 中 便于权限校验
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, loginUser.getPassword(), loginUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            // new Thread(() -> {
            //     // 更新登录状态保持的时间
            //     String rembMe = request.getHeader(Constants.REMB_ME);
            //     // 登录之后每次任何请求中前端都判断local中有没有值，在拦截器中hedaer= ture
            //     if (Boolean.parseBoolean(rembMe)) {
            //         redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 7, TimeUnit.DAYS);
            //     } else {
            //         redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 30, TimeUnit.MINUTES);
            //     }
            // }
            // ).start();
            // 采用线程池
            executor.execute(() -> {
                // 更新登录状态保持的时间
                String rembMe = request.getHeader(Constants.REMB_ME);
                // 登录之后每次任何请求中前端都判断local中有没有值，在拦截器中hedaer= ture
                if (Boolean.parseBoolean(rembMe)) {
                    redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 7, TimeUnit.DAYS);
                } else {
                    redisTemplate.expire(Constants.USER_TOKEN + loginUser.getId(), 30, TimeUnit.MINUTES);
                }
            });

        }
        filterChain.doFilter(request,response);
    }
}