package com.Bhalerao.ScrumPlay.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class SprintDto {
    private int sprintid;
    private int teamSize;
    private int sprintLength;
    private float scrumCallLength;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UserStoryDto> userStories;
}
