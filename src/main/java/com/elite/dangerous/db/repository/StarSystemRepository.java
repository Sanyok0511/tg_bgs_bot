package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StarSystemRepository extends JpaRepository<StarSystem, Long> {
    Optional<StarSystem> findStarSystemByName(String name);
}
