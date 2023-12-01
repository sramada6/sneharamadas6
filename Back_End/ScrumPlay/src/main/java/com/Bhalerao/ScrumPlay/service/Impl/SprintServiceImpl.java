package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.Sprint;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.SprintRepository;
import com.Bhalerao.ScrumPlay.service.SprintService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {

    private SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<SprintDto> findAllSprints() {
        List<Sprint> sprints = sprintRepository.findAll();
        return sprints.stream().map(this::mapToSprintDto).collect(Collectors.toList());
    }

    @Override
    public void saveSprint(SprintDto sprintDto) {
        Sprint sprint = new Sprint();
        sprint.setTeamSize(sprintDto.getTeamSize());
        sprint.setScrumCallLength(sprintDto.getScrumCallLength());
        sprint.setSprintLength(sprintDto.getSprintLength());
        sprint.setSprintid(sprintDto.getSprintid());
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        sprintRepository.save(sprint);
    }

    @Override
    public SprintDto findSprintById(long id) {
        Sprint s = sprintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint not found with ID: " + id));
        return mapToSprintDto(s);
    }



    private SprintDto mapToSprintDto(Sprint sprint) {
        SprintDto sdto = SprintDto.builder()
                .sprintid(sprint.getSprintid())
                .teamSize(sprint.getTeamSize())
                .sprintLength(sprint.getSprintLength())
                .scrumCallLength(sprint.getScrumCallLength())
                .startDate(sprint.getStartDate())
                .endDate(sprint.getEndDate()).build();
        return sdto;
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
