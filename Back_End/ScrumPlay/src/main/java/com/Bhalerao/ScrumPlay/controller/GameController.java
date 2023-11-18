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
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@Controller
public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);
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

    @GetMapping("/player-stories/{playerid}")
    public ResponseEntity<List<UserStoryDto>> getStoriesAssignedToPlayer(@PathVariable int playerid) {
        List<UserStoryDto> stories = userStoryService.getAllStoriesAssignedToPlayer(playerid);
        return ResponseEntity.ok(stories);
    }

    @GetMapping("/problem-stories/{statementid}")
    public ResponseEntity<List<UserStoryDto>> getUserStoriesByProblemId(@PathVariable int statementid) {
        List<UserStoryDto> userStories = userStoryService.getStoriesBystatementid(statementid);
        return ResponseEntity.ok(userStories);
    }


    @PutMapping("/backlog-modify/{userStoryId}")
    public ResponseEntity<UserStoryDto> modifyUserStory(@PathVariable int userStoryId, @RequestBody UserStoryDto updatedStoryDto) {
        UserStoryDto existingStory = userStoryService.findStoryById(userStoryId);

        if (existingStory == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedStoryDto.getStoryPoints() != 0) {
            existingStory.setStoryPoints(updatedStoryDto.getStoryPoints());
        }

        if (updatedStoryDto.getStatus() != null) {
            existingStory.setStatus(updatedStoryDto.getStatus());
        }

        if (updatedStoryDto.getStoryTitle() != null) {
            existingStory.setStoryTitle(updatedStoryDto.getStoryTitle());
        }

        if (updatedStoryDto.getStoryDescription() != null) {
            existingStory.setStoryDescription(updatedStoryDto.getStoryDescription());
        }

        if (updatedStoryDto.getAssignedTo() != null) {
            existingStory.setAssignedTo(updatedStoryDto.getAssignedTo());
        }

        if (updatedStoryDto.getCompletionDate() != null) {
            existingStory.setCompletionDate(updatedStoryDto.getCompletionDate());
        }

        if (updatedStoryDto.getWorkRemaining() != 0) {
            existingStory.setWorkRemaining(updatedStoryDto.getWorkRemaining());
        }

        userStoryService.saveStory(existingStory); // Use existingStory instead of updatedStoryDto

        log.info("Updated the user story!");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
