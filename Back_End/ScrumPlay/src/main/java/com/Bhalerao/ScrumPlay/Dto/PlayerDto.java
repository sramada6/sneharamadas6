package com.Bhalerao.ScrumPlay.Dto;


import com.Bhalerao.ScrumPlay.model.Sprint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerDto{

    private int playerid;
    private String playerName;
    private String playerRole;
    private int playerScore;

}
