package com.configuration;

import org.springframework.context.annotation.*;
import org.springframework.lang.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConf implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("https://kyc.firehay.com", "http://127.0.0.1:3000", "http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*");

  }
}
