package com.iamalokit.anotherblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iamalokit.anotherblog.interceptor.AdminLoginInterceptor;

@Configuration
public class AnotherBlogWebConfig implements WebMvcConfigurer {
	
	@Autowired
	private AdminLoginInterceptor adminLoginInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
    }
	
}
