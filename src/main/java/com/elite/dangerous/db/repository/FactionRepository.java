package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {
    Optional<Faction> findFractionByName(String name);
}
