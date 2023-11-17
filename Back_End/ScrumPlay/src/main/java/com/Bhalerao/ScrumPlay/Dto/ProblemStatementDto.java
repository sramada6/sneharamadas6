package com.Bhalerao.ScrumPlay.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProblemStatementDto {
    private int statementid;
    private String Statement;
    private int NumOfUserStories;
    private String comments;
}
