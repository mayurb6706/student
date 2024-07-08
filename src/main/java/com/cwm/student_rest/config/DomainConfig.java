package com.cwm.student_rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.cwm.student_rest.domain")
@EnableJpaRepositories("com.cwm.student_rest.repos")
@EnableTransactionManagement
public class DomainConfig {
}
