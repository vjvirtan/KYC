package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.*;
import org.springframework.boot.web.servlet.support.*;

import com.configuration.*;


@SpringBootApplication
@EnableConfigurationProperties(BaseConfigurationProperties.class)
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) throws ClassNotFoundException {
		// DepracatedConf.createDtoRuleClasses();
		SpringApplication.run(Application.class, args);

	}

}
