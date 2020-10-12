package com.olegdvd.temperature.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
@ComponentScan(basePackages = {"com.olegdvd.temperature"})
@EnableJpaRepositories(basePackages = {"com.olegdvd.temperature.repository"})
public class TemperatureConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    public Clock clock(){
        return Clock.systemDefaultZone();
    }
}
