package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;

import java.util.Optional;

public interface UserStoryService {
    Optional<UserStoryDto> getUserStoryById(String userStoryId);
    UserStoryDto updateUserStory(String userStoryId, String status, String assignedTo, Integer storyPoints, String description);
}
