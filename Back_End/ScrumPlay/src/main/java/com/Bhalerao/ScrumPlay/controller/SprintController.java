package com.Bhalerao.ScrumPlay.controller;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;

import com.Bhalerao.ScrumPlay.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class SprintController {
    private SprintService sprintService;

    @Autowired
    SprintController(SprintService sprintService)
    {
        this.sprintService = sprintService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSprint(@RequestBody SprintDto sprintDto) {
        try {
            sprintService.saveSprint(sprintDto);
            return ResponseEntity.ok("Sprint added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding sprint");
        }
    }
}
