package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.repository.CrudRepository;

public interface ConflictRepository extends CrudRepository<Conflict, Long> {
    @Override
    Iterable<Conflict> findAll();

    Conflict findConflictByFactionLeftOrFactionRightAndStarSystem(Faction factionLeft, Faction factionRight, StarSystem starSystem);

}
