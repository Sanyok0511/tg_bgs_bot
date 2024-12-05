package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailConflict {
    @JsonProperty("Faction1")
    private ConflictFaction factionLeft;
    @JsonProperty("Faction2")
    private ConflictFaction factionRight;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("WarType")
    private String warType;
}
