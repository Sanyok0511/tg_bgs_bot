package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDto {
    @JsonProperty(value = "Commander")
    private String commander;
    @JsonProperty(required = true)
    private String event;
    @JsonProperty(value = "StarSystem")
    private String starSystem;
    @JsonProperty(value = "Faction")
    private String faction;
    @JsonProperty(value = "DestinationSystem")
    private String destinationSystem;
    @JsonProperty(value = "TargetFaction")
    private String targetFaction;
    @JsonProperty(value = "Influence")
    private String influence;
    @JsonProperty(value = "Reputation")
    private String reputation;
    @JsonProperty(value = "LocalisedName")
    private String localisedName;
    @JsonProperty(value = "MissionID")
    private String missionId;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Reward")
    private String reward;
    @JsonProperty(value = "timestamp")
    private LocalDateTime timestamp;
    @JsonProperty(value = "Wing")
    private boolean wing;
    @JsonProperty(value = "Expiry")
    private LocalDateTime expiry;
    @JsonProperty(value = "Conflicts")
    private  List<DetailConflictDto> conflicts;
    @JsonProperty(value = "Factions")
    private List<FactionDto> factionDtos;
    @JsonProperty(value = "FactionEffects")
    private List<FactionEffectDto> factionEffectDtos;

    @Override
    public String toString() {
        return "Event{" +
                "commander='" + commander + '\'' +
                ", event='" + event + '\'' +
                ", starSystem='" + starSystem + '\'' +
                ", faction='" + faction + '\'' +
                ", destinationSystem='" + destinationSystem + '\'' +
                ", targetFaction='" + targetFaction + '\'' +
                ", influence='" + influence + '\'' +
                ", reputation='" + reputation + '\'' +
                ", localisedName='" + localisedName + '\'' +
                ", missionId='" + missionId + '\'' +
                ", name='" + name + '\'' +
                ", reward='" + reward + '\'' +
                ", timestamp=" + timestamp +
                ", wing=" + wing +
                ", expiry=" + expiry +
                ", conflicts=" + conflicts +
                ", factions=" + factionDtos +
                ", factionEffects=" + factionEffectDtos +
                '}';
    }
}