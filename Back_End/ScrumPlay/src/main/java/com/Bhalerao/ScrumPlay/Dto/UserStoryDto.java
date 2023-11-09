package com.Bhalerao.ScrumPlay.Dto;

import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoryDto {
    private Long id;
    private String storyDescription;
    private int storyPoints;
    private String status;
    private Player assignedPlayer;
    private ProblemStatement problemStatement;
}
