package com.elite.dangerous.api;

import com.elite.dangerous.db.entity.Faction;

public interface FactionService {
    Faction getOrCreateFaction(String name);
}
