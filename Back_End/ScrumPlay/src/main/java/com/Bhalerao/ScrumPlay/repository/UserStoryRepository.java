package com.Bhalerao.ScrumPlay.repository;

import com.Bhalerao.ScrumPlay.Dto.UserStoryDto;
import com.Bhalerao.ScrumPlay.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    List<UserStory> findByAssignedToPlayerid(int playerid);
    List<UserStory> findByProblemStatementStatementid(int statementid);
    List<UserStory> findBySprintSprintid(long sprintid);
    List<UserStoryDto> findAllByassignedToPlayerid(int playerId);
}
