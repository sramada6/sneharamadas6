package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.UserStoryRepository;
import com.Bhalerao.ScrumPlay.service.UserStoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService {

    private final UserStoryRepository userStoryRepository;

    @Autowired
    public UserStoryServiceImpl(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public Optional<UserStoryDto> getUserStoryById(String userStoryId) {
        Optional<UserStory> userStory = userStoryRepository.findById(userStoryId);
        return userStory.map(this::convertToDto);
    }

    @Override
    public UserStoryDto updateUserStory(String userStoryId, String status, String assignedTo, Integer storyPoints, String description) {
        Optional<UserStory> userStoryOptional = userStoryRepository.findById(userStoryId);

        if (userStoryOptional.isPresent()) {
            UserStory existingUserStory = userStoryOptional.get();

            existingUserStory.setStatus(status);
            existingUserStory.setAssignedTo(assignedTo);
            existingUserStory.setStoryPoints(storyPoints);
            existingUserStory.setDescription(description);

            userStoryRepository.save(existingUserStory);

            return convertToDto(existingUserStory);
        }

        return null;
    }

    @Override
    public List<UserStoryDto> getUserStoriesByProblemStatementId(String problemStatementId) {
        List<UserStory> userStories = userStoryRepository.findByProblemStatementId(problemStatementId);
        return userStories.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserStoryDto convertToDto(UserStory userStory) {
        return new UserStoryDto(
                userStory.getId(),
                userStory.getStatus(),
                userStory.getAssignedTo(),
                userStory.getStoryPoints(),
                userStory.getDescription(),
                userStory.getProblemStatementId()
        );
    }
}
