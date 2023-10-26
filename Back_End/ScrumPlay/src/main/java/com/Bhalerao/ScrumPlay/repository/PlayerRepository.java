package com.Bhalerao.ScrumPlay.repository;

import com.Bhalerao.ScrumPlay.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
