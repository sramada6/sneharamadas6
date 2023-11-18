package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.UserStory;
import com.Bhalerao.ScrumPlay.repository.PlayerRepository;
import com.Bhalerao.ScrumPlay.service.PlayerService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        player.setPlayerid(player.getPlayerid());
        playerRepository.save(player);
    }

    @Override
    public void savePlayers(List<PlayerDto> playerDtos) {
        // Convert PlayerDto objects to Player entities (if needed) and save them
        List<Player> players = playerDtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        playerRepository.saveAll(players);
    }

    @Override
    public PlayerDto findPlayerById(long id) {
        Player p = playerRepository.findById( id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with ID: " + id));
        return mapToPlayerDto(p);
    }

    // You may need a method to convert PlayerDto to Player entity
    private Player convertToEntity(PlayerDto playerDto) {
        Player player = new Player();
        player.setPlayerName(playerDto.getPlayerName());
        player.setPlayerRole(playerDto.getPlayerRole());
        return player;
    }
}