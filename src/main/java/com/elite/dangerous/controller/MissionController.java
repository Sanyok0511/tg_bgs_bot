package com.elite.dangerous.controller;

import com.elite.dangerous.dto.json.Event;
import com.elite.dangerous.processor.StarSystemProcessor;
import com.elite.dangerous.processor.MissionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    private MissionProcessor missionProcessor;
    private StarSystemProcessor starSystemProcessor;

    @Autowired
    public MissionController(
            MissionProcessor missionProcessor,
            StarSystemProcessor starSystemProcessor) {
        this.missionProcessor = missionProcessor;
        this.starSystemProcessor = starSystemProcessor;
    }
    @PostMapping("/event")
    public void addMission(@RequestBody Event event) {

        switch (event.getEvent()) {
            case "MissionAccepted" :
                System.out.println("Add mission: " + event);
                missionProcessor.addMission(event);
                break;
            case "MissionCompleted" :
            case "MissionFailed" :
                System.out.println("Update mission: " + event);
                missionProcessor.updateMission(event);
                break;
            case "Location":
            case "FSDJump":
                System.out.println("Update system: " + event);
                starSystemProcessor.updateStarSystem(event);
        }

    }

}
