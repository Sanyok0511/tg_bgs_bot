package com.elite.dangerous.db;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum WarType {
    WAR("war"),
    CIVILWAR("civilwar"),
    ELECTION("election");

    final static Map<String, WarType> values = Stream.of(WarType.values()).collect(Collectors.toMap(WarType::getStatus, warType -> warType));
    final private String value;

    WarType(String value) {
        this.value = value;
    }

    public String getStatus() {
        return value;
    }

    public static WarType of(String key) {
        return values.get(key);
    }
}
