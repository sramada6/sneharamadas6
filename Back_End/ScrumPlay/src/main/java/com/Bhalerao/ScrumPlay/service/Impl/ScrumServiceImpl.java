package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.Dto.ScrumDto;
import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.Scrum;
import com.Bhalerao.ScrumPlay.repository.ScrumRepository;

import com.Bhalerao.ScrumPlay.service.ScrumService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.ErrorManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ScrumServiceImpl implements ScrumService {

    private static final Logger logger = LoggerFactory.getLogger(ScrumServiceImpl.class);

    private ScrumRepository scrumRepository;

    @Autowired
    public ScrumServiceImpl(ScrumRepository scrumRepository) {
        this.scrumRepository = scrumRepository;
    }

    public void saveScrum(ScrumDto scrumDto){
        Scrum scrum = new Scrum();
        scrum.setScrumCallDuration(scrumDto.getScrumCallDuration());
        scrum.setSprint(scrumDto.getSprint());
        scrum.setScrumMaster(scrumDto.getScrumMaster());
        scrum.setPlayersPresent(scrumDto.getPlayersPresent());
        scrumRepository.save(scrum);
    }

    @Override
    public ScrumDto findScrumById(long id) {
        Scrum s = scrumRepository.findById( id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with ID: " + id));
        return mapToScrumDto(s);
    }

    private ScrumDto mapToScrumDto(Scrum s) {
        ScrumDto sdto = ScrumDto.builder()
                .scrumid(s.getScrumid())
                .playersPresent(s.getPlayersPresent())
                .sprint(s.getSprint())
                .scrumMaster(s.getScrumMaster())
                .scrumCallDuration(s.getScrumCallDuration()).build();
        return sdto;
    }

    public void updateScrumCallDuration(Long scrumId, int newScrumCallDuration) {
        try {
            Optional<Scrum> scrumOptional = scrumRepository.findById(scrumId);

            if (scrumOptional.isPresent()) {
                Scrum scrum = scrumOptional.get();
                scrum.setScrumCallDuration(newScrumCallDuration);
                scrumRepository.save(scrum);
                logger.info("Scrum Timer updated successfully for Scrum ID: {}", scrumId);
            } else {
                logger.warn("Scrum not found with ID: {}", scrumId);
            }
        } catch (Exception e) {
            logger.error("Error updating Scrum Timer for Scrum ID: {}", scrumId, e);
        }
    }
}
