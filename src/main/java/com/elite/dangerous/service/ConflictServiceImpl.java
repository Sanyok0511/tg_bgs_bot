package com.elite.dangerous.service;

import com.elite.dangerous.api.ConflictService;
import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.api.StarSystemService;
import com.elite.dangerous.db.WarType;
import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.ConflictRepository;
import com.elite.dangerous.dto.json.ConflictFactionDto;
import com.elite.dangerous.dto.json.DetailConflictDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConflictServiceImpl implements ConflictService {
    private final StarSystemService starSystemService;
    private final FactionService factionService;
    private final ConflictRepository conflictRepository;

    @Transactional
    public long updateConflicts(List<DetailConflictDto> conflicts, String starSystemName, LocalDateTime lastUpdate) {
        StarSystem starSystem = starSystemService.getOrCreateStarSystem(starSystemName);
        return conflicts.stream().map(conflict -> updateConflict(conflict, starSystem, lastUpdate)).count();
    }

    private Conflict updateConflict(DetailConflictDto detailConflictDto, StarSystem starSystem, LocalDateTime lastUpdate) {
        ConflictFactionDto conflictFactionDtoLeft = detailConflictDto.getFactionLeft();
        ConflictFactionDto conflictFactionDtoRigth = detailConflictDto.getFactionRight();

        Faction factionLeft = factionService.getOrCreateFaction(conflictFactionDtoLeft.getName());
        Faction factionRight = factionService.getOrCreateFaction(conflictFactionDtoRigth.getName());

        Conflict conflict = conflictRepository.findConflictByFactionsAndStarSystem(factionLeft, factionRight, starSystem);

        if (conflict == null) {
            conflict = new Conflict();
            log.trace("New conflict created");
        }

        conflict.setFactionLeft(factionLeft);
        conflict.setWonDateFactionLeft(conflictFactionDtoLeft.getWonDays());

        conflict.setFactionRight(factionRight);
        conflict.setWonDateFactionRight(conflictFactionDtoRigth.getWonDays());

        conflict.setWarType(WarType.of(detailConflictDto.getWarType()));
        conflict.setStarSystem(starSystem);
        conflict.setStatus(detailConflictDto.getStatus());
        conflict.setLastUpdate(lastUpdate);

        log.trace("Conflict information updated. Conflict: {}", conflict);
        return conflictRepository.save(conflict);
    }
}
