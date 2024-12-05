package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Faction;
import org.springframework.data.repository.CrudRepository;

public interface FactionRepository extends CrudRepository<Faction, Long> {
    Faction findFractionByName(String name);
}
