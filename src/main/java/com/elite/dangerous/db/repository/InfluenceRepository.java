package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluenceRepository extends CrudRepository<Influence, Long> {
    Influence findByStarSystemAndFaction(StarSystem starSystem, Faction faction);

    List<Influence> findAllByFaction(Faction faction);
}
