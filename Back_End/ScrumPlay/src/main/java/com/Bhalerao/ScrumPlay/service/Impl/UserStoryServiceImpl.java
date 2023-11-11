package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.UserStoryRepository;
import com.Bhalerao.ScrumPlay.service.UserStoryService;
//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

            // Set the updated values directly
            existingUserStory.setStatus(status);
            existingUserStory.setAssignedTo(assignedTo);
            existingUserStory.setStoryPoints(storyPoints);
            existingUserStory.setDescription(description);

            // Save the updated UserStory to the repository
            userStoryRepository.save(existingUserStory);

            // Convert UserStory entity to UserStoryDto using a mapper if needed
            return convertToDto(existingUserStory);
        }

        return null; // or handle the case where the UserStory is not found
    }

    private UserStoryDto convertToDto(UserStory userStory) {
        // Convert UserStory entity to UserStoryDto
        return new UserStoryDto(
                userStory.getId(),
                userStory.getStatus(),
                userStory.getAssignedTo(),
                userStory.getStoryPoints(),
                userStory.getDescription()
        );
    }
}
