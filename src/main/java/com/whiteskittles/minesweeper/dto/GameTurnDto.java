package com.whiteskittles.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GameTurnDto {

    @JsonProperty("game_id")
    String gameId;

    Integer col;

    Integer row;
}
