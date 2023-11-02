package com.Bhalerao.ScrumPlay.service;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SprintService {
    List<SprintDto> findAllSprints();

    void saveSprint(SprintDto sprintDto);
}
