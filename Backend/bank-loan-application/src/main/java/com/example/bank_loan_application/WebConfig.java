package com.example.bank_loan_application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply to all endpoints
                        .allowedOrigins("http://localhost:4200")  // Allow Angular dev server
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                        .allowedHeaders("*")  // Allow any header
                        .allowCredentials(true);  // Allow credentials
            }
        };
    }
}
