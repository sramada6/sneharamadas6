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
    private int calculatePlayerScore(Player player) {
        // Calculate playerScore based on user stories associated with the player
        List<UserStoryDto> userStories = userStoryService.getAllStoriesAssignedToPlayer(player.getPlayerid());

        // Filter user stories with status "Done"
        List<UserStoryDto> doneUserStories = userStories.stream()
                .filter(story -> "Done".equals(story.getStatus()))
                .collect(Collectors.toList());

        // Assuming a simple calculation based on the difference between completionDate and startDate
        int totalScore = doneUserStories.stream()
                .mapToInt(story -> calculateScoreForStory(story.getStartDate(), story.getCompletionDate()))
                .sum();

        return totalScore;
    }

    private int calculateScoreForStory(Date startDate, Date completionDate) {
        // Convert Date to LocalDate
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCompletionDate = completionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the difference between completionDate and startDate in days
        long daysDifference = ChronoUnit.DAYS.between(localStartDate, localCompletionDate);

        // Multiply the difference by 100 to get the score
        return (int) (daysDifference * 100);
    }



    @Override
    public void updatePlayerScore(int playerId, int newScore) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new EntityNotFoundException("Player not found with ID: " + playerId));

            player.setPlayerScore(newScore);
            playerRepository.save(player); // This line updates the database
        }


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