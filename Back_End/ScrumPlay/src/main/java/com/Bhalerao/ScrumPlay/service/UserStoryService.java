package com.Bhalerao.ScrumPlay.service;


import com.Bhalerao.ScrumPlay.Dto.ProblemStatementDto;
import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserStoryService {
    void saveStory(UserStoryDto storyDto);

    List<UserStoryDto> getAllUserStories();

    UserStoryDto findStoryById(long id);
    List<UserStoryDto> getAllStoriesAssignedToPlayer(int playerId);

    List<UserStoryDto> getStoriesBystatementid(int statementid);
}