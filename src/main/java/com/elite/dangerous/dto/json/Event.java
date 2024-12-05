package com.elite.dangerous.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Event {
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
    private Date timestamp;
    @JsonProperty(value = "Wing")
    private boolean wing;
    @JsonProperty(value = "Expiry")
    private Date expiry;
    @JsonProperty(value = "Conflicts")
    private  List<DetailConflict> conflicts;
    @JsonProperty(value = "Factions")
    private List<Faction> factions;
    @JsonProperty(value = "FactionEffects")
    private List<FactionEffect> factionEffects;

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
                ", factions=" + factions +
                ", factionEffects=" + factionEffects +
                '}';
    }
}