package com.elite.dangerous.service;

import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.api.MissionService;
import com.elite.dangerous.api.StarSystemService;
import com.elite.dangerous.db.StatusMission;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Mission;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.MissionRepository;
import com.elite.dangerous.dto.json.EventDto;
import com.elite.dangerous.dto.json.FactionEffectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;
    private final FactionService factionService;
    private final StarSystemService starSystemService;

    @Transactional
    public void addMission(EventDto eventDto) {
        Mission mission = createMission(eventDto);
        missionRepository.save(mission);
        log.trace("Mission created and saved. Mission: {} ", mission);
    }

    @Transactional
    public void updateMission(EventDto eventDto) {
        Mission mission = missionRepository.findMissionByMissionId(eventDto.getMissionId());
        if (mission == null) {
            mission = createMission(eventDto);
            log.debug("Mission not found to update. Mission created");
        }
        byte influence = getInfluence(eventDto);
        if (mission.getInfluence() == null || !mission.getInfluence().equals(influence)) {
            if (mission.getStatus().equals(StatusMission.MISSION_FAILED)) {
                mission.setInfluence((byte) (influence*-1));
            } else {
                mission.setInfluence(influence);
            }
        }
        mission.setStatus(StatusMission.of(eventDto.getEvent()));
        mission.setLastUpdate(eventDto.getTimestamp());
        missionRepository.save(mission);
        log.trace("Mission updated. Mission: {} ", mission);
    }

    private Mission createMission(EventDto eventDto) {
        Mission mission = missionRepository.findMissionByMissionId(eventDto.getMissionId());

        // перестраховка чтобы не записать одну и ту же миссию 2 раза
        if (mission == null) {
            mission = new Mission();
        }

        mission.setMissionId(eventDto.getMissionId());
        mission.setName(eventDto.getName());
        mission.setStatus(StatusMission.of(eventDto.getEvent()));

        Faction faction = factionService.getOrCreateFaction(eventDto.getFaction());
        mission.setFaction(faction);

        // If destinationFaction is null, then mission for current fraction in current starsystem
        if (eventDto.getTargetFaction() != null) {
            Faction destionationFaction = factionService.getOrCreateFaction(eventDto.getTargetFaction());
            mission.setDestinationFaction(destionationFaction);
        } else {
            mission.setDestinationFaction(faction);
        }


        mission.setInfluence(getInfluence(eventDto));

        StarSystem starSystem = starSystemService.getOrCreateStarSystem(eventDto.getStarSystem());
        mission.setStarSystem(starSystem);

        StarSystem destionationStarSystem = starSystemService.getOrCreateStarSystem(eventDto.getDestinationSystem());
        mission.setDestinationStarSystem(destionationStarSystem);

        mission.setLastUpdate(eventDto.getTimestamp());

        return mission;
    }

    private byte getInfluence (EventDto eventDto) {
        if (eventDto.getInfluence() != null) {
            return (byte) eventDto.getInfluence().length();
        }
        if (eventDto.getFactionEffectDtos() != null && !eventDto.getFactionEffectDtos().isEmpty()) {
            FactionEffectDto effect = eventDto.getFactionEffectDtos().stream().filter(factionEffectDto -> factionEffectDto.getInfluence().get(0).getTrend().equals("UpGood")).findFirst().get();
            return (byte) effect.getInfluence().get(0).getInfluence().length();
        }
        return 0;
    }
}
