package com.Bhalerao.ScrumPlay.service;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> findAllPlayers();
}
