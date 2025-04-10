package com.heysong.config;

import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;

@Configuration
@EnableConfigurationProperties(com.heysong.config.SwaggerProperties.class)
public class SwaggerConfig {
    @Resource
    private SwaggerProperties swaggerProperties;
    @Resource
    private Environment environment;

    @Bean
    public Docket api() {
        //swagger3 , apiInfo 对象
        //考虑不同的开发环境下需不需要生成接口文档  只在开发环境  弄个开关 检测到是开发环境的话就生成Docket 对象 的enable方法
        boolean flag = false;
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile : activeProfiles) {
            if ("dev".equals(profile)) {
                flag = true;
                break;
            }
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .select()
                // 指定扫描的包路径，只对该包下的控制器生成文档
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        // //将配置信息属性对象中的属性赋值 给ApiInfo 对象
        // return new ApiInfo(swaggerProperties.getTitle(), swaggerProperties.getDescription(), swaggerProperties.getVersion(), swaggerProperties.getTermsOfServiceUrl(),
        //         new Contact(swaggerProperties.getName(), swaggerProperties.getName(), swaggerProperties.getEmail()),
        //         swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(),
        //         new HashSet<>());

        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .contact(new Contact(swaggerProperties.getName(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                .build();
    }
}