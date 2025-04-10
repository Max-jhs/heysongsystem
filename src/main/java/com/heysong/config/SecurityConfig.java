package com.heysong.config;

import com.heysong.config.filter.JwtVerityFilter;
import com.heysong.config.handler.MyAccessDeniedHandler;
import com.heysong.config.handler.MyAuthenticationFailureHandler;
import com.heysong.config.handler.MyAuthenticationSuccessHandler;
import com.heysong.config.handler.MyLogoutHandler;
import com.heysong.model.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @Author: 8912
 * @Date: 2025/2/27 2:12
 * @Version: v1.0.0
 * @Description:
 **/
@Configuration
public class SecurityConfig {
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyAccessDeniedHandler  myAccessDeniedHandler;
    @Resource
    private JwtVerityFilter jwtVerityFilter;
    @Resource
    private MyLogoutHandler logoutHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.headers((head) ->{
            head.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
        }).formLogin((form) -> {
                    form.loginProcessingUrl(Constants.LOGIN_URI)
                            // 前端formdata传参
                            .usernameParameter(Constants.LOGIN_ACT)
                            .passwordParameter(Constants.LOGIN_PWD)
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler).permitAll();
        }).authorizeHttpRequests((request)-> {
                    request.requestMatchers("/swagger-ui.html","/api/customer/exportExcel","/api/customer/exportSelectedExcel", "/swagger-ui/**", "/v2/api-docs/**").permitAll();
                    request.anyRequest().authenticated();
        }).exceptionHandling((exceptionHandling) ->{
                    exceptionHandling.accessDeniedHandler(myAccessDeniedHandler);
                })
                .csrf((csrf)->{
                    csrf.disable();
                }).sessionManagement((manager)->{
                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }).cors((cors) -> {
                    cors.configurationSource(configurationSource());
                }).addFilterBefore(jwtVerityFilter, LogoutFilter.class).
                logout((logout) ->{
                    logout.logoutUrl("/api/logout")
                            .addLogoutHandler(logoutHandler);
                }).
                build();
    }
    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}