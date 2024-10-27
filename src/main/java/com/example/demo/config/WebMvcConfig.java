package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/css/**")
                .addResourceLocations("classpath:/static/resource/css/");
        registry.addResourceHandler("/resource/js/**")
                .addResourceLocations("classpath:/static/resource/js/");
        registry.addResourceHandler("/resource/images/**")
                .addResourceLocations("classpath:/static/resource/images/");
        registry.addResourceHandler("/resource/client/**")
                .addResourceLocations("classpath:/static/resource/client/");
    }
}
