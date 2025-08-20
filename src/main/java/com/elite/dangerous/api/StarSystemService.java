package com.elite.dangerous.api;

import com.elite.dangerous.db.entity.StarSystem;

public interface StarSystemService {
    StarSystem getOrCreateStarSystem(String name);
}
