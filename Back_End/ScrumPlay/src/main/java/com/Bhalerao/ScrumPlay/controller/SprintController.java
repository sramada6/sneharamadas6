package com.Bhalerao.ScrumPlay.controller;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;

import com.Bhalerao.ScrumPlay.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SprintController {
    private SprintService sprintService;

    @Autowired
    SprintController(SprintService sprintService)
    {
        this.sprintService = sprintService;
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
    public ResponseEntity<Map<String, Float>> getTimerById(@PathVariable Long id) {
        SprintDto s = sprintService.findSprintById(id);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Float> response = new HashMap<>();
        response.put("scrumCallLength:", s.getScrumCallLength());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
