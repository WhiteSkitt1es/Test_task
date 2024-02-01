package com.whiteskittles.minesweeper.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class GameInfoDto {

    String gameId;
    Integer width;
    Integer height;
    Integer minesCount;
    Boolean completed;
    String[][] field;
}
