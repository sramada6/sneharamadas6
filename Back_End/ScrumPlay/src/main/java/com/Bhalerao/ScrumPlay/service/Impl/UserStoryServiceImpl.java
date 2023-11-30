
package com.Bhalerao.ScrumPlay.service.Impl;



import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.Sprint;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.UserStoryRepository;
import com.Bhalerao.ScrumPlay.service.UserStoryService;


import jakarta.persistence.EntityNotFoundException;



import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    private final UserStoryRepository userStoryRepository;

    public UserStoryServiceImpl(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public void saveStory(UserStoryDto storyDto) {

        Optional<UserStory> existingStoryOptional = userStoryRepository.findById((long)storyDto.getStoryid());

        if (existingStoryOptional.isPresent()) {
            UserStory existingStory = existingStoryOptional.get();
            existingStory.setStoryPoints(storyDto.getStoryPoints());
            existingStory.setStatus(storyDto.getStatus());
            existingStory.setStoryDescription(storyDto.getStoryDescription());
            existingStory.setAssignedTo(storyDto.getAssignedTo() != null ? storyDto.getAssignedTo() : null);
            existingStory.setCompletionDate(storyDto.getCompletionDate());
            existingStory.setWorkRemaining(storyDto.getWorkRemaining());
            existingStory.setStoryTitle(storyDto.getStoryTitle());
            userStoryRepository.save(existingStory);
        }else{
            UserStory userStory = new UserStory();
            userStory.setStoryid(storyDto.getStoryid());
            userStory.setStoryPoints(storyDto.getStoryPoints());
            userStory.setStatus(storyDto.getStatus());
            userStory.setStoryDescription(storyDto.getStoryDescription());
            userStory.setAssignedTo(storyDto.getAssignedTo() != null ? storyDto.getAssignedTo() : null);
            userStory.setProblemStatement(storyDto.getProblemStatement() != null ? storyDto.getProblemStatement() : null);
            userStory.setCreationDate(storyDto.getCreationDate());
            userStory.setStartDate(storyDto.getStartDate());
            userStory.setCompletionDate(storyDto.getCompletionDate());
            userStory.setWorkRemaining(storyDto.getWorkRemaining());
            userStory.setStoryTitle(storyDto.getStoryTitle());
            userStoryRepository.save(userStory);
        }
    }

    @Override
    public List<UserStoryDto> getAllUserStories() {
        List<UserStory> userStories = userStoryRepository.findAll();
        return userStories.stream()
                .map(this::mapToStoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserStoryDto findStoryById(long id) {
        UserStory userStory = userStoryRepository.findById( id)
                .orElseThrow(() -> new EntityNotFoundException("User Story not found with ID: " + id));
        return mapToStoryDto(userStory);
    }

    public List<UserStoryDto> getAllStoriesAssignedToPlayer(int playerid) {
        List<UserStory> stories = userStoryRepository.findByAssignedToPlayerid(playerid);

        // Map the entities to DTOs
        return stories.stream()
                .map(this::mapToStoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserStoryDto> getStoriesBystatementid(int statementid) {
        List<UserStory> stories = userStoryRepository.findByProblemStatementStatementid(statementid);

        // Map the entities to DTOs
        return stories.stream()
                .map(this::mapToStoryDto)
                .collect(Collectors.toList());
    }
    public SprintDto getSprintDataFromUserStory(long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new EntityNotFoundException("User Story not found with ID: " + userStoryId));

        return mapToSprintDto(userStory.getSprint());
    }

    private SprintDto mapToSprintDto(Sprint sprint) {
        return SprintDto.builder()
                .sprintid(sprint.getSprintid())
                .teamSize(sprint.getTeamSize())
                .sprintLength(sprint.getSprintLength())
                .scrumCallLength(sprint.getScrumCallLength())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate())
                .build();
    }

    private UserStoryDto mapToStoryDto(UserStory story) {
        UserStoryDto usdto = UserStoryDto.builder()
                .storyid(story.getStoryid())
                .storyPoints(story.getStoryPoints())
                .status(story.getStatus())
                .storyDescription(story.getStoryDescription())
                .assignedTo(story.getAssignedTo() != null ? story.getAssignedTo() : null)
                .problemStatement(story.getProblemStatement() != null ? story.getProblemStatement() : null)
                .creationDate(story.getCreationDate())
                .startDate(story.getStartDate())
                .completionDate(story.getCompletionDate())
                .workRemaining(story.getWorkRemaining()).storyTitle(story.getStoryTitle())
                .sprint((story.getSprint()) != null ? story.getSprint() : null)
                .build();
        return usdto;
    }

}
