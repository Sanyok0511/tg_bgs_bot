package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Commander;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Commander, Long> {
    Commander findByUsername(String username);
}
