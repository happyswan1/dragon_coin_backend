package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许跨域访问的路径
                        .allowedOrigins("http://localhost:8080") // 前端服务器地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
                        .allowCredentials(true)
                        .allowedHeaders("*"); // 允许的请求头
            }
        };
    }
}
