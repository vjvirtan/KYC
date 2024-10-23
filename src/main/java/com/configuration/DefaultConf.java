package com.configuration;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.http.*;

import org.springframework.web.client.*;


import com.enums.*;
import com.validation.*;

@Configuration
@EnableAspectJAutoProxy
public class DefaultConf {

  @Bean
  Map<Object, ValidationRule> defaultValidationRules() {
    Map<Object, ValidationRule> rules = new HashMap<>();
    firstName(rules);
    return rules;
  }

  private void firstName(Map<Object, ValidationRule> rules) {
    ValidationRule.builder()
        .allowedChars(FieldEnum.NAME.toString())
        .mandatory(false)
        .build();
  }

  // TODO: THIS PROPABLY WORKS WITHOUT
  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  HttpHeaders headers() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

}
