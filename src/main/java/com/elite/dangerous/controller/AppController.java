package com.elite.dangerous.controller;

import com.elite.dangerous.tg.dto.InfluenceDisplay;
import com.elite.dangerous.tg.preview.InfluencePreviewMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AppController {
    private InfluencePreviewMaker preparator;
    @Autowired
    public AppController(InfluencePreviewMaker preparator) {
        this.preparator = preparator;
    }

    @GetMapping("/getInfluence")
    public InfluenceDisplay getInfluenceData(@RequestParam String name) {
        log.trace("Get influence data for {}", name);
        return preparator.prepareDataToDisplayInfluence(name);
    }
}
