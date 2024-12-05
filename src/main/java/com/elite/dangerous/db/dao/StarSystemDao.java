package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.StarSystemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StarSystemDao {
    private StarSystemRepository starSystemRepository;

    @Autowired
    public StarSystemDao(StarSystemRepository starSystemRepository) {
        this.starSystemRepository = starSystemRepository;
    }

    public StarSystem findOrCreateStarSystem(String name) {
        StarSystem starSystem = findStarSystemByName(name);
        if (starSystem == null) {
            starSystem = new StarSystem(name);
            starSystemRepository.save(starSystem);
            log.debug("Star system created and saved. Star system: {}", starSystem);
        }
        return starSystem;
    }

    public StarSystem findStarSystemByName(String name) {
        return starSystemRepository.findStarSystemByName(name);
    }

    public void save(StarSystem starSystem) {
        starSystemRepository.save(starSystem);
    }
}
