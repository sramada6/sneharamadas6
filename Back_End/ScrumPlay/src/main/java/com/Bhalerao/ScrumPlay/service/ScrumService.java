package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.ScrumDto;

import org.springframework.stereotype.Service;

@Service
public interface ScrumService {
    void saveScrum(ScrumDto sprintDto);
    ScrumDto findScrumById(long id);
    void updateScrumCallDuration(Long scrumId, int newScrumCallDuration);
}
