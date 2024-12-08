package com.elite.dangerous.processor;

import com.elite.dangerous.db.WarType;
import com.elite.dangerous.db.dao.ConflictDao;
import com.elite.dangerous.db.dao.FactionDao;
import com.elite.dangerous.db.dao.InfluenceDao;
import com.elite.dangerous.db.dao.StarSystemDao;
import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.dto.json.ConflictFaction;
import com.elite.dangerous.dto.json.DetailConflict;
import com.elite.dangerous.dto.json.Event;
import com.elite.dangerous.dto.json.Faction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
public class StarSystemProcessor {
    private StarSystemDao starSystemDao;
    private FactionDao factionDao;
    private InfluenceDao influenceDao;
    private ConflictDao conflictDao;

    public void setStarSystemDao(StarSystemDao starSystemDao) {
        this.starSystemDao = starSystemDao;
    }

    public void setFactionDao(FactionDao factionDao) {
        this.factionDao = factionDao;
    }

    public void setInfluenceDao(InfluenceDao influenceDao) {
        this.influenceDao = influenceDao;
    }

    public void setConflictDao(ConflictDao conflictDao) {
        this.conflictDao = conflictDao;
    }

    @Transactional
    public void updateStarSystem(Event event) {
        String starSystemName = event.getStarSystem();
        Date lastUpdate = event.getTimestamp();
        StarSystem starSystem = starSystemDao.findOrCreateStarSystem(starSystemName);

        List<Faction> factions = event.getFactions();

        if (factions != null) {
            factions.forEach(faction -> saveFaction(faction, starSystem, lastUpdate));
        }

        List<DetailConflict> conflicts = event.getConflicts();
        if (conflicts != null) {
            conflicts.forEach(detailConflict -> saveConflict(detailConflict, starSystem, lastUpdate));
        }
    }

    private void saveFaction(Faction faction, StarSystem system, Date lastUpdate) {
        String factionName = faction.getName();
        com.elite.dangerous.db.entity.Faction factionEntity = factionDao.findOrCreateFactionByName(factionName);
        if (factionEntity == null) {
            factionEntity = new com.elite.dangerous.db.entity.Faction();
            factionEntity.setName(factionName);

        }
        Influence influence;
        if (factionEntity.getId() != null && system.getId() != null) {
            influence = influenceDao.findByStarSystemAndFaction(system, factionEntity);
            if (influence == null) {
                influence = new Influence();
                influence.setStarSystem(system);
                influence.setFaction(factionEntity);
                influence.setLastUpdate(lastUpdate);
            }
            if (influence.getInfluence() == null || !influence.getInfluence().equals(faction.getInfluence())) {
                influence.setInfluence(faction.getInfluence());
                influence.setLastUpdate(lastUpdate);
                influenceDao.save(influence);
            }
        } else {
            influence = new Influence();
            influence.setStarSystem(system);
            influence.setFaction(factionEntity);
            influence.setInfluence(faction.getInfluence());
            influence.setLastUpdate(lastUpdate);
        }
        influenceDao.save(influence);
        log.trace("Fraction information update. Fraction: {}", faction);
    }

    private void saveConflict(DetailConflict detailConflict, StarSystem starSystem, Date lastUpdate) {
        ConflictFaction conflictFactionLeft = detailConflict.getFactionLeft();
        ConflictFaction conflictFactionRigth = detailConflict.getFactionRight();

        com.elite.dangerous.db.entity.Faction factionLeft = factionDao.findOrCreateFactionByName(conflictFactionLeft.getName());
        com.elite.dangerous.db.entity.Faction factionRight = factionDao.findOrCreateFactionByName(conflictFactionRigth.getName());

        Conflict conflict = conflictDao.findByStarSystemAndFactions(factionLeft, factionRight, starSystem);
        if (conflict == null) {
            conflict = conflictDao.findByStarSystemAndFactions(factionRight, factionLeft, starSystem);
        }

        if (conflict == null) {
            conflict = new Conflict();
            log.trace("New conflict created");
        }

        conflict.setFactionLeft(factionLeft);
        conflict.setWonDateFactionLeft(conflictFactionLeft.getWonDays());

        conflict.setFactionRight(factionRight);
        conflict.setWonDateFactionRight(conflictFactionRigth.getWonDays());

        conflict.setWarType(WarType.of(detailConflict.getWarType()));
        conflict.setStarSystem(starSystem);
        conflict.setStatus(detailConflict.getStatus());
        conflict.setLastUpdate(lastUpdate);
        conflictDao.save(conflict);
        log.trace("Conflict information updated. Conflict: {} ", conflict);
    }

}
