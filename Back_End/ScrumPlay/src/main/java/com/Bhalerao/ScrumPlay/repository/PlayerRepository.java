package com.Bhalerao.ScrumPlay.repository;

import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<UserStory> findBysprintSprintid(int sprintid);
}
