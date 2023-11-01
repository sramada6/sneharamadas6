package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    List<PlayerDto> findAllPlayers();

    void savePlayer(PlayerDto playerDTO);
}
