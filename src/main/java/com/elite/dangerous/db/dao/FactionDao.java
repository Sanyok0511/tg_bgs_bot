package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.repository.FactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FactionDao {
    private FactionRepository factionRepository;

    @Autowired
    public FactionDao(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    public Faction findOrCreateFactionByName(String name) {
        Faction faction = findFactionByName(name);
        if (faction == null) {
            faction = new Faction(name);
            factionRepository.save(faction);
            log.debug("Fraction created and saved. Fraction name: {} ", faction);
        }
        return faction;
    }

    public Faction findFactionByName(String name) {
        return factionRepository.findFractionByName(name);
    }

    public void save(Faction faction) {
        factionRepository.save(faction);
    }
}
