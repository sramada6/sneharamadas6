package com.Bhalerao.ScrumPlay.service.Impl;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.model.Sprint;
import com.Bhalerao.ScrumPlay.repository.SprintRepository;
import com.Bhalerao.ScrumPlay.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository sprintRepository;

    @Autowired
    public SprintServiceImpl(SprintRepository sprintRepository){
        this.sprintRepository = sprintRepository;
    }
    @Override
    public List<SprintDto> findAllSprints() {
        List<Sprint> sprints = sprintRepository.findAll();
        return sprints.stream().map((sprint) -> mapToSprintDto(sprint)).collect(Collectors.toList());
    }

    @Override
    public void saveSprint(SprintDto sprintDto) {
        Sprint sprint = new Sprint();
        sprint.setTeamSize(sprintDto.getTeamSize());
        sprint.setScrumCallLength(sprintDto.getScrumCallLength());
        sprint.setSprintLength(sprintDto.getSprintLength());
        sprint.setSprintid(sprintDto.getSprintid());
        sprintRepository.save(sprint);
    }

    private SprintDto mapToSprintDto(Sprint sprint) {
        SprintDto sdto = SprintDto.builder()
                .sprintid(sprint.getSprintid())
                .teamSize(sprint.getTeamSize())
                .sprintLength(sprint.getSprintLength())
                .scrumCallLength(sprint.getScrumCallLength()).build();
        return sdto;
    }
}
