package com.whiteskittles.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class NewGameDto {

    Integer width;

    Integer height;

    @JsonProperty("mines_count")
    Integer minesCount;
}
