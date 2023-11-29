package com.Bhalerao.ScrumPlay.service;


import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import com.Bhalerao.ScrumPlay.model.Sprint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SprintService {
    List<SprintDto> findAllSprints();

    void saveSprint(SprintDto sprintDto);

    SprintDto findSprintById(long id);

}
