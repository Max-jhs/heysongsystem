package com.heysong.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *  json转换工具类
 */
public class JSONUtils {
    public static final ObjectMapper MAPPER = new ObjectMapper();


    public static String beanToJson(Object obj) {
        try {
            // 将任何对象转成json字符串
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToBean(String userJson, Class<T> clazz) {

        try {
            // 将json字符串转成对象
            return MAPPER.readValue(userJson, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
