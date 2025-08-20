package com.elite.dangerous.service;

import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.repository.FactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FactionServiceImpl implements FactionService {
    private FactionRepository factionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Faction getOrCreateFaction(String name) {
        return factionRepository.findFractionByName(name)
                .orElse(factionRepository.save(new Faction(name)));
    }
}
