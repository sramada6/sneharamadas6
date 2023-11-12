package com.Bhalerao.ScrumPlay.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserStoryDto {
    private String id;
    private String status;
    private String assignedTo;
    private Integer storyPoints;
    private String description;
    private String problemStatementId;
}
