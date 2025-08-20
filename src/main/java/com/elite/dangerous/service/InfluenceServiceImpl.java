package com.elite.dangerous.service;

import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.api.InfluenceService;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.InfluenceRepository;
import com.elite.dangerous.dto.json.FactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class InfluenceServiceImpl implements InfluenceService {
    private final FactionService factionService;
    private final StarSystemServiceImpl starSystemServiceImpl;
    private final InfluenceRepository influenceRepository;

    public List<Influence> getInfluence(Faction faction) {
        return influenceRepository.findAllByFaction(faction);
    }

    @Transactional
    public void updateInfluence(List<FactionDto> factions, String starSystemName, LocalDateTime lastUpdate) {
        StarSystem starSystem = starSystemServiceImpl.getOrCreateStarSystem(starSystemName);
        factions.forEach(faction -> updateInfluence(faction, starSystem, lastUpdate));
    }

    private void updateInfluence(FactionDto factionDto, StarSystem starSystem, LocalDateTime lastUpdate) {
        Faction faction = factionService.getOrCreateFaction(factionDto.getName());

        Influence influence = influenceRepository.findByStarSystemAndFaction(starSystem, faction);
        if (influence == null) {
            influence = new Influence();
            influence.setStarSystem(starSystem);
            influence.setFaction(faction);
        }

        influence.setLastUpdate(lastUpdate);
        influence.setInfluence(factionDto.getInfluence());
        influenceRepository.save(influence);
        log.trace("Fraction information update. Fraction: {}", factionDto);
    }
}
