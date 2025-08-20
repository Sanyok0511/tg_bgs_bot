package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FactionEffectInfluenceDto {
    @JsonProperty(value = "Influence")
    private String influence;
    @JsonProperty(value = "Trend")
    private String trend;
}
