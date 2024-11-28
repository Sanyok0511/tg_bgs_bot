package org.example.db.repository;

import org.example.db.entity.StarSystem;
import org.springframework.data.repository.CrudRepository;

public interface StarSystemReposiroty extends CrudRepository<StarSystem, Long> {
}
