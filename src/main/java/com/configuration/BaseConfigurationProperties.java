package com.configuration;

import org.springframework.boot.context.properties.*;

@ConfigurationProperties("base")
public record BaseConfigurationProperties(String URL, String FILE_PATH) {

}
