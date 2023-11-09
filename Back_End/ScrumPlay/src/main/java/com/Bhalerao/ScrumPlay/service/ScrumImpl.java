package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.ScrumDto;

import org.springframework.stereotype.Service;

@Service
public interface ScrumImpl {
    void saveScrum(ScrumDto sprintDto);
}
