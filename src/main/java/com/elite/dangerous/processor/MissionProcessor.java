package com.elite.dangerous.processor;

import com.elite.dangerous.db.StatusMission;
import com.elite.dangerous.db.dao.FactionDao;
import com.elite.dangerous.db.dao.MissionDao;
import com.elite.dangerous.db.dao.StarSystemDao;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Mission;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.dto.json.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class MissionProcessor {


    private MissionDao missionRepository;
    private FactionDao factionDao;
    private StarSystemDao starSystemDao;

    public void setMissionDao(MissionDao missionRepository) {
        this.missionRepository = missionRepository;
    }

    public void setFactionDao(FactionDao factionRepository) {
        this.factionDao = factionRepository;
    }

    public void setStarSystemDao(StarSystemDao starSystemRepository) {
        this.starSystemDao = starSystemRepository;
    }

    @Transactional
    public void addMission(Event event) {
        Mission mission = createMission(event);
        missionRepository.save(mission);
        log.trace("Mission created and saved. Mission: {} ", mission);
    }

    @Transactional
    public void updateMission(Event event) {
        Mission mission = missionRepository.findMissionByMissionId(event.getMissionId());
        if (mission == null) {
            mission = createMission(event);
            log.debug("Mission not found to update. Mission created");
        }
        if (mission.getInfluence() == null) {
            mission.setInfluence(getInfluence(event));
        }
        missionRepository.save(mission);
        log.trace("Mission saved. Mission: {} ", mission);
    }

    private Mission createMission(Event event) {
        Mission mission = missionRepository.findMissionByMissionId(event.getMissionId());

        // перестраховка чтобы не записать одну и ту же миссию 2 раза
        if (mission == null) {
            mission = new Mission();
        }

        mission.setMissionId(event.getMissionId());
        mission.setName(event.getName());
        mission.setStatus(StatusMission.of(event.getEvent()));

        Faction faction = factionDao.findOrCreateFactionByName(event.getFaction());
        mission.setFaction(faction);

        Faction destionationFaction = factionDao.findOrCreateFactionByName(event.getTargetFaction());
        mission.setDestinationFaction(destionationFaction);

        mission.setInfluence(getInfluence(event));

        StarSystem starSystem = starSystemDao.findOrCreateStarSystem(event.getStarSystem());
        mission.setStarSystem(starSystem);

        StarSystem destionationStarSystem = starSystemDao.findOrCreateStarSystem(event.getDestinationSystem());
        mission.setDestinationStarSystem(destionationStarSystem);
        mission.setTimestamp(event.getTimestamp());

        return mission;
    }

    private byte getInfluence (Event event) {
        if (event.getInfluence() != null) {
            return (byte) event.getInfluence().length();
        }
        if (event.getFactionEffects() != null && !event.getFactionEffects().isEmpty() && !event.getFactionEffects().get(0).getInfluence().isEmpty()) {
            return (byte) event.getFactionEffects().get(0).getInfluence().get(0).getInfluence().length();
        }
        return 0;
    }
}
