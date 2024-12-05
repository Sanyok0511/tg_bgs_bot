package com.elite.dangerous.tg.preview;

import com.elite.dangerous.db.dao.FactionDao;
import com.elite.dangerous.db.dao.InfluenceDao;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import com.elite.dangerous.tg.dto.InfluenceDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InfluencePreviewMaker {
    private InfluenceDao influenceDao;
    private FactionDao factionDao;

    @Autowired
    public InfluencePreviewMaker(
            InfluenceDao influenceDao,
            FactionDao factionDao) {
        this.influenceDao = influenceDao;
        this.factionDao = factionDao;
    }

    public InfluenceDisplay prepareDataToDisplayInfluence(String factionName) {
        Faction faction = factionDao.findFactionByName(factionName);
        List<Influence> influences = influenceDao.findAllByFaction(faction);
        InfluenceDisplay influenceDisplay = new InfluenceDisplay(factionName);
        for (Influence influence : influences) {
            influenceDisplay.addInfluenceInformation(influence.getStarSystem().getName(), String.format("%.2f", influence.getInfluence()));
        }
        return influenceDisplay;
    }
}
