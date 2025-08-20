package com.elite.dangerous.api;

import com.elite.dangerous.dto.json.EventDto;

public interface MissionService {
    void addMission(EventDto eventDto);
    void updateMission(EventDto eventDto);
}
