package org.spring_example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("org.spring_example")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {

}
