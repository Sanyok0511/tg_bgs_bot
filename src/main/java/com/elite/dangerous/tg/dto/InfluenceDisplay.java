package com.elite.dangerous.tg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class InfluenceDisplay {
    private String faction;
    private List<InfluenceStarSystem> influenceInformation = new ArrayList<>();

    public InfluenceDisplay() {}
    public InfluenceDisplay(String faction) {
        this.faction = faction;
    }

    public void addInfluenceInformation(String starSystem, String influence) {
        influenceInformation.add(new InfluenceStarSystem(starSystem, influence));
    }

    @Override
    public String toString() {
        return faction + "\n" + influenceInformation.stream().map(InfluenceStarSystem::toString).collect(Collectors.joining("\n"));
    }

    @AllArgsConstructor
    private class InfluenceStarSystem {
        private String starSystem;
        private String influence;


        @Override
        public String toString() {
            return starSystem + " - " + influence;
        }
    }
}
