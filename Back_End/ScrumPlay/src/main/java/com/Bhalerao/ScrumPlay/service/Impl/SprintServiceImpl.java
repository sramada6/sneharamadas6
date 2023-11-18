package com.Bhalerao.ScrumPlay.service.Impl;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.model.Sprint;
import com.Bhalerao.ScrumPlay.repository.SprintRepository;
import com.Bhalerao.ScrumPlay.service.SprintService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
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
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
        sprintRepository.save(sprint);
    }

    @Override
    public SprintDto findSprintById(long id) {
        Sprint s = sprintRepository.findById( id)
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
}
