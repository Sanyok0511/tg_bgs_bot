package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Conflict;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConflictRepository extends CrudRepository<Conflict, Long> {
    @Override
    Iterable<Conflict> findAll();

    @Query(value = """
        SELECT c
        FROM Conflict c
        WHERE (c.factionLeft = ?1 AND c.factionRight = ?2) OR (c.factionLeft = ?2 AND c.factionRight = ?1) AND c.starSystem = ?3
        """)
    Conflict findConflictByFactionsAndStarSystem(Faction factionLeft, Faction factionRight, StarSystem starSystem);
}
