package com.elite.dangerous.api;

import com.elite.dangerous.dto.json.FactionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface InfluenceService {
    void updateInfluence(List<FactionDto> factions, String starSystemName, LocalDateTime lastUpdate);
}
