package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailConflictDto {
    @JsonProperty("Faction1")
    private ConflictFactionDto factionLeft;
    @JsonProperty("Faction2")
    private ConflictFactionDto factionRight;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("WarType")
    private String warType;
}
