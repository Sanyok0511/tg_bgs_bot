package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FactionDto {
    @JsonProperty("Allegiance")
    private String allegiance;
    @JsonProperty("Influence")
    private float influence;
    @JsonProperty("Name")
    private String name;

}
