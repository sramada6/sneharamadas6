package com.Bhalerao.ScrumPlay.controller;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;

import com.Bhalerao.ScrumPlay.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SprintController {
    private SprintService sprintService;

    @Autowired
    SprintController(SprintService sprintService)
    {
        this.sprintService = sprintService;
    }
    
}
