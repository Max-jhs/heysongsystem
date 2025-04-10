package com.heysong.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @Author: 8912
 * @Date: 2025/2/25 23:48
 * @Version: v1.0.0
 * @Description: 响应码枚举
 **/

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

public enum CodeEnum {
    FAIL(400, "失败！"),
    OK(200, "成功！"),
    LOGIN_OK(200, "认证成功！"),
    LOGIN_ERROR(400, "认证失败！"),
    LOGOUT_SUCCESS(200, "退出登录成功！"),
    USER_INFO(200, "获取用户信息成功！"),
    TOKEN_INVALID(10002, "token不合法！"),
    TOKEN_NOT_EXIST(10001, "redis中token为空"),
    TOKEN_NULL(10003, "请求token不存在"),
    TOKEN_NOT_EQUALS(10004, "token不相等"),
    FAIL_ACCOUNT(400, "账号或密码有误！"),
    LOG_OUT("退出成功！");

    @Getter
    private Integer code;
    @Getter
    @NonNull
    private String msg;

    // CodeEnum(String msg){
    //     this.msg = msg;
    // } RequireArgConstructor + nonnull   = 单个参数构造器
}