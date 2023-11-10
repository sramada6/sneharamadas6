package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.ScrumDto;

import org.springframework.stereotype.Service;

@Service
public interface ScrumService {
    void saveScrum(ScrumDto sprintDto);
}
