package com.sipc.clockin.config;

import com.sipc.clockin.handler.interceptor.LoginAuthenticationInterceptor;
import com.sipc.clockin.handler.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;
    private LoginAuthenticationInterceptor loginAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        todo：仅是demo，根据需求自定义路径
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/**");

//        registry.addInterceptor(loginAuthenticationInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/inlet/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST","PUT", "DELETE")
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
