package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Commander;
import org.springframework.data.repository.CrudRepository;

public interface CommanderRepository extends CrudRepository<Commander, Long> {
    @Override
    Iterable<Commander> findAll();
}
