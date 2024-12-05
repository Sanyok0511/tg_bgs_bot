package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.StarSystem;
import org.springframework.data.repository.CrudRepository;

public interface StarSystemRepository extends CrudRepository<StarSystem, Long> {
    StarSystem findStarSystemByName(String name);
}
