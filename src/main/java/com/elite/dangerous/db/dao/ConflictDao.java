package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.ConflictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConflictDao {
    private ConflictRepository conflictRepository;

    @Autowired
    public ConflictDao(ConflictRepository conflictRepository) {
        this.conflictRepository = conflictRepository;
    }

    public Conflict findByStarSystemAndFaction(Faction faction, StarSystem starSystem) {
        return conflictRepository.findConflictByFactionLeftAndFactionRightAndStarSystem(faction, faction, starSystem);
    }

    public Conflict findByStarSystemAndFactions(Faction factionLeft, Faction factionRight, StarSystem starSystem) {
        return conflictRepository.findConflictByFactionLeftAndFactionRightAndStarSystem(factionLeft, factionRight, starSystem);
    }

    public void save(Conflict conflict) {
        conflictRepository.save(conflict);
    }
}
