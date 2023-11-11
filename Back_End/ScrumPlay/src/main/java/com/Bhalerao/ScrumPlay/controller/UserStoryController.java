package com.Bhalerao.ScrumPlay.controller;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.service.UserStoryService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/userStories")
public class UserStoryController {

    //private static final Logger logger = LoggerFactory.getLogger(UserStoryController.class);

    private final UserStoryService userStoryService;

    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/{userStoryId}")
    public ResponseEntity<UserStoryDto> getUserStory(@PathVariable String userStoryId) {
        Optional<UserStoryDto> userStoryDto = userStoryService.getUserStoryById(userStoryId);
        return userStoryDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userStoryId}/modify")
    public ResponseEntity<UserStoryDto> modifyUserStory(@PathVariable String userStoryId,
                                                        @RequestParam(required = false) String status,
                                                        @RequestParam(required = false) String assignedTo,
                                                        @RequestParam(required = false) Integer storyPoints,
                                                        @RequestParam(required = false) String description) {
        UserStoryDto updatedUserStory = userStoryService.updateUserStory(userStoryId, status, assignedTo, storyPoints, description);
        return updatedUserStory != null
                ? ResponseEntity.ok(updatedUserStory)
                : ResponseEntity.notFound().build();
    }
}
