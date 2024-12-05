package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConflictFaction {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Stake")
    private String stake;
    @JsonProperty("WonDays")
    private byte wonDays;
}
