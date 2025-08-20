package com.elite.dangerous.api;

import com.elite.dangerous.dto.json.DetailConflictDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ConflictService {
    long updateConflicts(List<DetailConflictDto> conflicts, String starSystemName, LocalDateTime lastUpdate);
}
