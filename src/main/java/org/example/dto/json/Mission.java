package org.example.dto.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonFormat
public class Mission {
    @JsonProperty(value = "DestinationStation")
    private String destinationStation;
    @JsonProperty(value = "DestinationSystem")
    private String destinationSystem;
    @JsonProperty(required = true)
    private String event;
    @JsonProperty(value = "Expiry")
    private Date expiry;
    @JsonProperty(value = "Faction", required = true)
    private String faction;
    @JsonProperty(value = "Influence")
    private String influence;
    @JsonProperty(value = "LocalisedName")
    private String localisedName;
    @JsonProperty(value = "MissionID", required = true)
    private String missionId;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Reputation")
    private String reputation;
    @JsonProperty(value = "Reward")
    private String reward;
    @JsonProperty(value = "TargetFaction")
    private String targetFaction;
    @JsonProperty(value = "timestamp")
    private Date timestamp;
    @JsonProperty(value = "Wing")
    private boolean wing;

    @Override
    public String toString() {
        return "Mission{" +
                "destinationStation='" + destinationStation + '\'' +
                ", destinationSystem='" + destinationSystem + '\'' +
                ", event='" + event + '\'' +
                ", expiry=" + expiry +
                ", faction='" + faction + '\'' +
                ", influence='" + influence + '\'' +
                ", localisedName='" + localisedName + '\'' +
                ", missionId='" + missionId + '\'' +
                ", Name='" + name + '\'' +
                ", reputation='" + reputation + '\'' +
                ", reward='" + reward + '\'' +
                ", targetFaction='" + targetFaction + '\'' +
                ", timestamp=" + timestamp +
                ", wing=" + wing +
                '}';
    }
}