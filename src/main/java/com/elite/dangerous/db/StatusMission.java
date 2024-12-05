package com.elite.dangerous.db;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StatusMission {
    MISSION_ACCEPTED("MissionAccepted"),
    MISSION_COMPLETED("MissionCompleted"),
    MISSION_FAILED("MissionFailed");

    final static Map<String, StatusMission> values = Stream.of(StatusMission.values()).collect(Collectors.toMap(StatusMission::getStatus, statusMission -> statusMission));
    final private String value;

    StatusMission(String value) {
        this.value = value;
    }

    public String getStatus() {
        return value;
    }

    public static StatusMission of(String key) {
        return values.get(key);
    }
}
