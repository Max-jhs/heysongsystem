package com.heysong.model.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 8912
 * @Date: 2025/2/26 0:26
 * @Version: v1.0.0
 * @Description: 统一web响应对象
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class R {
    private Integer code;
    private String msg;
    private Object data;
    public static R OK(Object obj) {
        return R.builder().code(CodeEnum.USER_INFO.getCode()).msg(CodeEnum.USER_INFO.getMsg()).data(obj).build();
    }

    public static R OK(CodeEnum codeEnum) {
        return R.builder().code(OK().getCode()).msg(codeEnum.getMsg()).build();
    }

    public static R OK(String msg,Object obj) {
        return R.builder().code(CodeEnum.OK.getCode()).msg(msg).data(obj).build();
    }

    public static R OK() {
        return R.builder().code(CodeEnum.OK.getCode()).msg(CodeEnum.OK.getMsg()).build();
    }

    public static R FAIL() {
        return R.builder().code(CodeEnum.FAIL.getCode()).msg(CodeEnum.FAIL.getMsg()).build();
    }

}