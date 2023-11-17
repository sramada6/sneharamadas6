package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.ScrumDto;
import com.Bhalerao.ScrumPlay.model.Scrum;
import com.Bhalerao.ScrumPlay.repository.ScrumRepository;

import com.Bhalerao.ScrumPlay.service.ScrumService;
import org.springframework.stereotype.Service;

@Service
public class ScrumServiceImpl implements ScrumService {
    private ScrumRepository scrumRepository;

    public void saveScrum(ScrumDto scrumDto){
        Scrum scrum = new Scrum();
        scrum.setScrumCallDuration(15);
        scrum.setSprint(scrumDto.getSprint());
        scrum.setScrumMaster(scrumDto.getScrumMaster());
        scrum.setPlayersPresent(scrumDto.getPlayersPresent());
        scrumRepository.save(scrum);
    }
}
