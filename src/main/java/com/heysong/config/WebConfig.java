package com.heysong.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.heysong.convert.StringDateConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/3/18 18:08
 * @Version: v1.0.0
 * @Description:
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // json xml  http 请求体 数据绑定
    // 前后端分离的项目中，前端通过 JSON 格式发送请求数据，
    // 后端使用消息转换器将 JSON 数据转换为 Java 对象进行处理，
    // 处理完成后再将 Java 对象转换为 JSON 格式返回给前端。
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    // 添加日期转换器  url ，
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringDateConvert());
    }
    // 添加json 转换器 搭配mappingjackson2  专门处理json数据
    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(module)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .dateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .timeZone(java.util.TimeZone.getTimeZone("GMT+8"))
                .build();
        return objectMapper;
    }
}