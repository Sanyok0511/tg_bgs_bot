package com.elite.dangerous.controller;

import com.elite.dangerous.api.ConflictService;
import com.elite.dangerous.api.InfluenceService;
import com.elite.dangerous.api.MissionService;
import com.elite.dangerous.dto.json.EventDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class EventController {
    private MissionService missionService;
    private InfluenceService influenceService;
    private ConflictService conflictService;

    @PostMapping("/event")
    public void processEvent(@RequestBody EventDto eventDto) {

        switch (eventDto.getEvent()) {
            case "MissionAccepted" :
                System.out.println("Add mission: " + eventDto);
                missionService.addMission(eventDto);
                break;
            case "MissionCompleted" :
            case "MissionFailed" :
                System.out.println("Update mission: " + eventDto);
                missionService.updateMission(eventDto);
                break;
            case "Location":
            case "FSDJump":
                System.out.println("Update system: " + eventDto);
                influenceService.updateInfluence(eventDto.getFactionDtos(), eventDto.getStarSystem(), eventDto.getTimestamp());
                conflictService.updateConflicts(eventDto.getConflicts(), eventDto.getStarSystem(), eventDto.getTimestamp());
        }
    }
}
