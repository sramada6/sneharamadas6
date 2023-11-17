package com.Bhalerao.ScrumPlay.Dto;

import com.Bhalerao.ScrumPlay.model.Sprint;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScrumDto {
    private Long scrumid;
    private int scrumCallDuration;
    private Sprint sprint;
}
