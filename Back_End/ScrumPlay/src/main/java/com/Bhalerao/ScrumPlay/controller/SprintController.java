package com.Bhalerao.ScrumPlay.controller;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.service.ScrumService;
import com.Bhalerao.ScrumPlay.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SprintController {
    private SprintService sprintService;
    private final ScrumService scrumService;

    @Autowired
    SprintController(SprintService sprintService, ScrumService scrumService)
    {
        this.sprintService = sprintService;
        this.scrumService = scrumService;
    }

    @GetMapping("/sprint/{id}")
    public ResponseEntity<SprintDto> getSprintById(@PathVariable Long id) {
        SprintDto s = sprintService.findSprintById(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/sprint/timer/{id}")
    public ResponseEntity<Float> getTimerById(@PathVariable Long id) {
        SprintDto s = sprintService.findSprintById(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(s.getScrumCallLength(), HttpStatus.OK);
    }

    @GetMapping("/sprint/length/{id}")
    public ResponseEntity<Integer> getLengthById(@PathVariable Long id) {
        SprintDto s = sprintService.findSprintById(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(s.getSprintLength(), HttpStatus.OK);
    }

    @PutMapping("/sprint/update-call-duration/{scrumId}")
    public ResponseEntity<String> updateScrumCallDuration(
            @PathVariable Long scrumId,
            @RequestParam int newScrumCallDuration
    ) {
        try {
            scrumService.updateScrumCallDuration(scrumId, newScrumCallDuration);
            return ResponseEntity.ok("Scrum Call Duration updated successfully.");
        }  catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PutMapping("/sprint/story-points/{sprintId}")
    public ResponseEntity<String> updateStoryPointsCompleted(
            @PathVariable long sprintId,
            @RequestParam int storyPointsCompleted) {
        System.out.println("Story points completed Started...");
        SprintDto existingSprint = sprintService.findSprintById(sprintId);
        existingSprint.setStoryPointsCompleted(storyPointsCompleted);
        sprintService.saveSprint(existingSprint);

        System.out.println("Story points completed updated successfully");
        return ResponseEntity.ok("Story points completed updated successfully");
    }

}
