package com.Bhalerao.ScrumPlay.Dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDto{

    private int playerid;
    private String playerName;
    private String playerRole;

}
