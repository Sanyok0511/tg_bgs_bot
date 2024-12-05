package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.InfluenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfluenceDao {
    private InfluenceRepository influenceRepository;

    @Autowired
    public InfluenceDao(InfluenceRepository influenceRepository) {
        this.influenceRepository = influenceRepository;
    }

    public Influence findByStarSystemAndFaction(StarSystem starSystem, Faction faction) {
        return influenceRepository.findByStarSystemAndFaction(starSystem, faction);
    }

    public List<Influence> findAllByFaction(Faction faction) {
        return influenceRepository.findAllByFaction(faction);
    }
    public void save(Influence influence) {
        influenceRepository.save(influence);
    }
}
