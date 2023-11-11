package com.Bhalerao.ScrumPlay.repository;

import com.Bhalerao.ScrumPlay.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, String> {

    // You can add custom query methods here if needed
}
