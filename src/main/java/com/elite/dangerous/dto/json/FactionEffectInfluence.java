package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FactionEffectInfluence {
    @JsonProperty(value = "Influence")
    private String influence;
}
