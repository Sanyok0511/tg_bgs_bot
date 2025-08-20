package com.elite.dangerous.tg.preview;


import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.service.InfluenceServiceImpl;
import com.elite.dangerous.tg.dto.InfluenceDisplay;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class InfluencePreviewMaker {
    private InfluenceServiceImpl influenceServiceImpl;
    private FactionService factionService;

    public InfluenceDisplay prepareDataToDisplayInfluence(String factionName) {
        Faction faction = factionService.getOrCreateFaction(factionName);
        List<Influence> influences = influenceServiceImpl.getInfluence(faction);
        InfluenceDisplay influenceDisplay = new InfluenceDisplay(factionName);
        for (Influence influence : influences) {
            influenceDisplay.addInfluenceInformation(influence.getStarSystem().getName(), String.format("%.2f", influence.getInfluence()));
        }
        return influenceDisplay;
    }
}
