package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConflictRepository extends CrudRepository<Conflict, Long> {
    @Override
    Iterable<Conflict> findAll();

    Conflict findConflictByFactionLeftAndFactionRightAndStarSystem(Faction factionLeft, Faction factionRight, StarSystem starSystem);
    List<Conflict> findConflictsByFactionLeftAndFactionRightAndStarSystem(Faction factionLeft, Faction factionRight, StarSystem starSystem);

}
