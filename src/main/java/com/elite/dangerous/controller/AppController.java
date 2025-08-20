package com.elite.dangerous.controller;

import com.elite.dangerous.api.MissionService;
import com.elite.dangerous.tg.dto.InfluenceDisplay;
import com.elite.dangerous.tg.preview.InfluencePreviewMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AppController {
    private final InfluencePreviewMaker preparator;
    private final MissionService missionService;

    @GetMapping("/getInfluence")
    public ResponseEntity<InfluenceDisplay> getInfluenceData(@RequestParam(name = "name") String name) {
        log.trace("Get influence data for {}", name);
        return ResponseEntity.ok(preparator.prepareDataToDisplayInfluence(name));
    }

//    @GetMapping("/mission")
//    public String getMissionStatistic(@RequestParam String faction) {
//        List<MissionSummary> summaries = missionService.getMissionSummaryByFaction(faction);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (MissionSummary summary : summaries) {
//            stringBuilder.append(summary.getSystemName() + " - accepted: " + summary.getAccepted() + ", competed: " + summary.getCompleted() +
//                    ", failed: " + summary.getFailed());
//        }
//        return stringBuilder.toString();
//    }
}
