package com.Bhalerao.ScrumPlay.Dto;

import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserStoryDto {
    private int storyid;
    private String storyDescription;
    private int storyPoints;
    private String status;
    private Player assignedTo;
    private ProblemStatement problemStatement;
    private Date creationDate;
    private Date startDate;
    private Date completionDate;
    private int workRemaining;
}
