package com.elite.dangerous.configuration;

import com.elite.dangerous.db.dao.*;
import com.elite.dangerous.processor.StarSystemProcessor;
import com.elite.dangerous.processor.MissionProcessor;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConfigurationElite {

    @Bean
    @Autowired
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
    @Bean
    @Autowired
    public StarSystemProcessor startSystemProcessor(
            StarSystemDao starSystemDao,
            FactionDao factionDao,
            InfluenceDao influenceDao,
            ConflictDao conflictDao
            ) {
        StarSystemProcessor starSystemProcessor = new StarSystemProcessor();
        starSystemProcessor.setFactionDao(factionDao);
        starSystemProcessor.setInfluenceDao(influenceDao);
        starSystemProcessor.setStarSystemDao(starSystemDao);
        starSystemProcessor.setConflictDao(conflictDao);
        return starSystemProcessor;
    }

    @Bean
    @Autowired
    public MissionProcessor missionProcessor(
            StarSystemDao starSystemDao,
            FactionDao factionDao,
            MissionDao missionDao
            ) {
        MissionProcessor missionProcessor = new MissionProcessor();
        missionProcessor.setStarSystemDao(starSystemDao);
        missionProcessor.setMissionDao(missionDao);
        missionProcessor.setFactionDao(factionDao);

        return missionProcessor;
    }
}
