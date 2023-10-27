package com.Bhalerao.ScrumPlay.controller;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.service.PlayerService;
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
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    PlayerController(PlayerService playerService)
    {
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDto playerDto) {
        try {
            playerService.savePlayer(playerDto);
            return ResponseEntity.ok("Player added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding player");
        }
    }
}
