package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.repository.PlayerRepository;
import com.Bhalerao.ScrumPlay.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    @Override
    public List<PlayerDto> findAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map((player) -> mapToPlayerDto(player)).collect(Collectors.toList());
    }

    private PlayerDto mapToPlayerDto(Player player) {
        PlayerDto pdto = PlayerDto.builder()
                        .playerid(player.getPlayerid())
                                .playerName(player.getPlayerName())
                                        .playerRole(player.getPlayerRole()).build();
        return pdto;
    }

    @Override
    public void savePlayer(PlayerDto playerDTO) {
        Player player = new Player();
        player.setPlayerName(playerDTO.getPlayerName());
        player.setPlayerRole(playerDTO.getPlayerRole());
        playerRepository.save(player);
    }
}
