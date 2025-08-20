package com.elite.dangerous.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class ConfigurationElite {

    @Bean
    @Profile("!test")
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
