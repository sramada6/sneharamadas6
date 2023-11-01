package com.Bhalerao.ScrumPlay.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SprintDto {
    private int sprintid;
    private int teamSize;
    private int sprintLength;
    private float scrumCallLength;
}
