package com.heysong.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 8912
 * @Date: 2025/3/18 18:14
 * @Version: v1.0.0
 * @Description:
 **/

public class StringDateConvert implements Converter<String, Date> {
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public Date convert(String source) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("string date 解析异常");
        }
    }
}