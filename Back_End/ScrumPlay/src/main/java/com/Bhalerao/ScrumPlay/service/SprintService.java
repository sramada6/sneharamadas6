package com.Bhalerao.ScrumPlay.service;


import com.Bhalerao.ScrumPlay.Dto.SprintDto;

import java.util.List;

public interface SprintService {
    List<SprintDto> findAllSprints();
}
