package org.example.controller;

import org.example.dto.json.Mission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    @PostMapping("/mission")
    public void addMission(@RequestBody Mission mission) {
        System.out.println(mission);
    }
}
