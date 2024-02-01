package com.whiteskittles.minesweeper.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class ErrorDto {
    String error;
}
