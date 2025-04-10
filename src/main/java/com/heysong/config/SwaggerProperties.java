package com.heysong.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 8912
 * @Date: 2025/3/4 16:23
 * @Version: v1.0.0
 * @Description: swagger属性对象 版本，版权，联系方式，url，包名  从外部（配置属性）读取swagger2 -属性
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties {
    //注意变量名字转驼峰
    private String basePackage;
    private String name;
    private String url;
    private String email;
    private String title;
    private String description;
    private String license;
    private String licenseUrl;
    private String termsOfServiceUrl;
    private String version;
}