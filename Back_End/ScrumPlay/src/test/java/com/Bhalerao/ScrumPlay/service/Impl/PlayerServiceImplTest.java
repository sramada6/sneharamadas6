package com.Bhalerao.ScrumPlay.service.Impl;

import com.Bhalerao.ScrumPlay.Dto.PlayerDto;
import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.repository.PlayerRepository;
import com.Bhalerao.ScrumPlay.service.Impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllPlayers() {
        // Arrange
        List<Player> mockPlayers = Arrays.asList(
                new Player(1, "John Doe", "Developer"),
                new Player(2, "Jane Doe", "Scrum Master")
        );
        when(playerRepository.findAll()).thenReturn(mockPlayers);

        // Act
        List<PlayerDto> result = playerService.findAllPlayers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getPlayerName());
        assertEquals("Jane Doe", result.get(1).getPlayerName());
    }

    @Test
    void testSavePlayer() {
        // Arrange
        PlayerDto playerDto = new PlayerDto(1, "John Doe", "Developer");
        when(playerRepository.save(any(Player.class))).thenReturn(new Player());

        // Act
        playerService.savePlayer(playerDto);

        // Assert
        verify(playerRepository, times(1)).save(any(Player.class));
    }

    @Test
    void testSavePlayers() {
        // Arrange
        List<PlayerDto> playerDtos = Arrays.asList(
                new PlayerDto(1, "John Doe", "Developer"),
                new PlayerDto(2, "Jane Doe", "Scrum Master")
        );
        when(playerRepository.saveAll(anyIterable())).thenReturn(Arrays.asList(new Player(), new Player()));

        // Act
        playerService.savePlayers(playerDtos);

        // Assert
        verify(playerRepository, times(1)).saveAll(anyIterable());
    }

    @Test
    void testFindPlayerById() {
        // Arrange
        long playerId = 1;
        Player mockPlayer = new Player((int) playerId, "John Doe", "Developer");
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(mockPlayer));

        // Act
        PlayerDto result = playerService.findPlayerById(playerId);

        // Assert
        assertEquals("John Doe", result.getPlayerName());
        assertEquals("Developer", result.getPlayerRole());
    }

    @Test
    void testFindPlayerById_ThrowsEntityNotFoundException() {
        // Arrange
        long playerId = 1;
        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> playerService.findPlayerById(playerId));
    }
}
