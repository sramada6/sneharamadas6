package com.Bhalerao.ScrumPlay.Dto;


import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.Bhalerao.ScrumPlay.model.Player;
import com.Bhalerao.ScrumPlay.model.ProblemStatement;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserStoryDto {
    private Long storyid;
    private String storyDescription;
    private String storyTitle;
    private int storyPoints;
    private String status;
    private Player assignedTo;
    private ProblemStatement problemStatement;
    private Date creationDate;
    private Date startDate;
    private Date completionDate;
    private int workRemaining;


    public Long getId() {
        return storyid;
    }
}
