package com.Bhalerao.ScrumPlay.repository;

import com.Bhalerao.ScrumPlay.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    List<UserStory> findByAssignedToPlayerId(Long playerId);
}
