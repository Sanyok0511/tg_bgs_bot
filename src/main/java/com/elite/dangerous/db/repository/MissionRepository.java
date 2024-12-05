package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Mission;
import org.springframework.data.repository.CrudRepository;

public interface MissionRepository extends CrudRepository<Mission, Long> {
    Mission findMissionByMissionId(String missionId);
}
