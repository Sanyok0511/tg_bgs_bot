package com.elite.dangerous.service;

import com.elite.dangerous.api.StarSystemService;
import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.db.repository.StarSystemRepository;
import com.elite.dangerous.tg.processor.MessageUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StarSystemServiceImpl implements StarSystemService {
    private final StarSystemRepository starSystemRepository;
    private final MessageUpdater messageUpdater;

    //TODO: добавить обновление сообщений ТГ
    public void updateStatistic(StarSystem starSystem) {
        messageUpdater.updateInfluence(starSystem);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StarSystem getOrCreateStarSystem(String name) {
        return starSystemRepository.findStarSystemByName(name)
                .orElse(starSystemRepository.save(new StarSystem(name)));
    }
}
