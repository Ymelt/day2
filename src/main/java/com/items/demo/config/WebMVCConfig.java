package com.items.demo.config;

import com.items.demo.Interceptor.LoginInterceptor;
import com.items.demo.Interceptor.LoginTeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private LoginTeacherInterceptor loginTeacherInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/admin/**");
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/save");


//        registry.addInterceptor(loginTeacherInterceptor)
//                .addPathPatterns("/atTeacher/**");
    }
}
