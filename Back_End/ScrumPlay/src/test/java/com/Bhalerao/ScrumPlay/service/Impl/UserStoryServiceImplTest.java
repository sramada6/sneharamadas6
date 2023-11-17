package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.UserStoryRepository;
import com.Bhalerao.ScrumPlay.service.Impl.UserStoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserStoryServiceImplTest {

    @Mock
    private UserStoryRepository userStoryRepository;

    @InjectMocks
    private UserStoryServiceImpl userStoryService;

    @Test
    void saveStory_shouldSaveUserStory() {
        // Arrange
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Create a sample UserStoryDto
        UserStoryDto storyDto = UserStoryDto.builder()
                .storyid(1)
                .storyPoints(5)
                .status("In Progress")
                .storyDescription("Example user story description")
                // ... (other fields)
                .build();

        // Assuming the story with the given ID doesn't exist in the database
        when(userStoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        userStoryService.saveStory(storyDto);

        // Assert
        verify(userStoryRepository, times(1)).save(any(UserStory.class));
    }
}
