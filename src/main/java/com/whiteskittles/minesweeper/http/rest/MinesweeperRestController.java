package com.whiteskittles.minesweeper.http.rest;

import com.whiteskittles.minesweeper.dto.GameInfoDto;
import com.whiteskittles.minesweeper.dto.GameTurnDto;
import com.whiteskittles.minesweeper.dto.NewGameDto;
import com.whiteskittles.minesweeper.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MinesweeperRestController {

    private final GameService service;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public GameInfoDto startGame(@RequestBody NewGameDto newGameDto) {
        return service.startGame(newGameDto);
    }

    @PostMapping("/turn")
    @ResponseStatus(HttpStatus.OK)
    public GameInfoDto playGame(@RequestBody GameTurnDto gameTurnDto) {
        return service.playGame(gameTurnDto);
    }
}
