package com.borawski.jobtaskone.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@Configuration
@EntityScan("com.borawski.jobtaskone.domain")
public class DomainConfig {

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}