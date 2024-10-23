package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.configuration.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ClassNotFoundException {
		// DepracatedConf.createDtoRuleClasses();
		SpringApplication.run(Application.class, args);

	}

}
