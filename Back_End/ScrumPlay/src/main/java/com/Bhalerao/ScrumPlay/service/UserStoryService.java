package com.Bhalerao.ScrumPlay.service;


import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import org.springframework.stereotype.Service;

@Service
public interface UserStoryService {
    void saveStory(UserStoryDto storyDto);
}
