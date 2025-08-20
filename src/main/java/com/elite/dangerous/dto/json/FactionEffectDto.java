package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FactionEffectDto {
    @JsonProperty(value = "Faction")
    private String faction;
    @JsonProperty(value = "Influence")
    private List<FactionEffectInfluenceDto> influence;
}
