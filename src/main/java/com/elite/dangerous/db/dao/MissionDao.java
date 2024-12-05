package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.entity.Mission;
import com.elite.dangerous.db.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MissionDao {
    private MissionRepository missionRepository;

    @Autowired
    public MissionDao(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission findMissionByMissionId(String missionId) {
        return missionRepository.findMissionByMissionId(missionId);
    }

    public void save(Mission mission) {
        missionRepository.save(mission);
    }
}
