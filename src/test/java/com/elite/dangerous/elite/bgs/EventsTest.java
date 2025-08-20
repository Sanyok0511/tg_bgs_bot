package com.elite.dangerous.elite.bgs;

import com.elite.dangerous.controller.EventController;
import com.elite.dangerous.elite.bgs.annotations.IT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
@AutoConfigureMockMvc
@Commit
public class EventsTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String startGame = """
            { "timestamp":"2025-08-10T08:08:02Z", "event":"Location", "DistFromStarLS":312.351433, "Docked":false, "OnFoot":true, "StarSystem":"Zenufangwe", "SystemAddress":2871051756953, "StarPos":[78.15625,94.34375,-42.12500], "SystemAllegiance":"Independent", "SystemEconomy":"$economy_Industrial;", "SystemEconomy_Localised":"Промышленность", "SystemSecondEconomy":"$economy_Refinery;", "SystemSecondEconomy_Localised":"Переработка", "SystemGovernment":"$government_Corporate;", "SystemGovernment_Localised":"Корпоративная", "SystemSecurity":"$SYSTEM_SECURITY_high;", "SystemSecurity_Localised":"Высок. ур. безопасности", "Population":5162192, "Body":"Murray Port", "BodyID":15, "BodyType":"Station", "Powers":[ "Felicia Winters" ], "PowerplayState":"Unoccupied", "PowerplayConflictProgress":[ { "Power":"Felicia Winters", "ConflictProgress":0.000017 } ], "Factions":[ { "Name":"Future of Zenufangwe", "FactionState":"CivilWar", "Government":"Democracy", "Influence":0.104523, "Allegiance":"Federation", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":-75.000000, "RecoveringStates":[ { "State":"Outbreak", "Trend":0 } ], "ActiveStates":[ { "State":"CivilWar" } ] }, { "Name":"Liberty Party of Zenufangwe", "FactionState":"None", "Government":"Dictatorship", "Influence":0.038191, "Allegiance":"Independent", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":-12.120000, "RecoveringStates":[ { "State":"PirateAttack", "Trend":0 } ] }, { "Name":"Zenufangwe Solutions", "FactionState":"None", "Government":"Corporate", "Influence":0.051256, "Allegiance":"Independent", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":15.116700 }, { "Name":"Zenufangwe Gold Drug Empire", "FactionState":"None", "Government":"Anarchy", "Influence":0.010050, "Allegiance":"Independent", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":8.910000 }, { "Name":"Defence Force of Zenufangwe", "FactionState":"CivilWar", "Government":"Dictatorship", "Influence":0.104523, "Allegiance":"Independent", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":-75.000000, "ActiveStates":[ { "State":"CivilWar" } ] }, { "Name":"Excalibur Arms and Munitions", "FactionState":"None", "Government":"Corporate", "Influence":0.192965, "Allegiance":"Alliance", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "MyReputation":100.000000 }, { "Name":"Lords of Universe Corp.", "FactionState":"None", "Government":"Corporate", "Influence":0.498492, "Allegiance":"Independent", "Happiness":"$Faction_HappinessBand2;", "Happiness_Localised":"Счастье", "SquadronFaction":true, "MyReputation":100.000000 } ], "SystemFaction":{ "Name":"Lords of Universe Corp." }, "Conflicts":[ { "WarType":"civilwar", "Status":"active", "Faction1":{ "Name":"Future of Zenufangwe", "Stake":"Galactic Energy Research", "WonDays":2 }, "Faction2":{ "Name":"Defence Force of Zenufangwe", "Stake":"Platt's Progress", "WonDays":3 } } ] }
            """;

    @Autowired
    private EventController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        //EventDto eventDto = objectMapper.readValue(startGame, EventDto.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/event").contentType("application/json").content(startGame))
                .andExpect(status().isOk());
    }
}
