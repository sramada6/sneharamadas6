package com.Bhalerao.ScrumPlay.controller;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.service.ScrumService;
import com.Bhalerao.ScrumPlay.service.SprintService;
import com.Bhalerao.ScrumPlay.service.UserStoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private final SprintService sprintService;
    private final ScrumService scrumService;
    private final UserStoryService userStoryService;

    @Autowired
    public GameController(
            SprintService sprintService,
            ScrumService scrumService,
            UserStoryService userStoryService) {
        this.sprintService = sprintService;
        this.scrumService = scrumService;
        this.userStoryService = userStoryService;
    }
    @GetMapping("/backlog")
    public ResponseEntity<List<UserStoryDto>> getAllUserStories() {
        List<UserStoryDto> userStories = userStoryService.getAllUserStories();
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }

    @GetMapping("/backlog/{id}")
    public ResponseEntity<UserStoryDto> getUserStoryById(@PathVariable Long id) {
        UserStoryDto userStory = userStoryService.findStoryById(id);
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }

    @GetMapping("/stories/{playerId}")
    public ResponseEntity<List<UserStoryDto>> getStoriesAssignedToPlayer(@PathVariable Long playerId) {
        List<UserStoryDto> stories = userStoryService.getAllStoriesAssignedToPlayer(playerId);
        return ResponseEntity.ok(stories);
    }
}
