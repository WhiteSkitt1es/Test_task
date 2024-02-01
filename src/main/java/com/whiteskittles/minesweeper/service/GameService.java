package com.whiteskittles.minesweeper.service;

import com.whiteskittles.minesweeper.dto.GameInfoDto;
import com.whiteskittles.minesweeper.dto.GameTurnDto;
import com.whiteskittles.minesweeper.dto.NewGameDto;
import com.whiteskittles.minesweeper.game.BuildGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private BuildGame buildGame;

    public GameInfoDto startGame(NewGameDto newGameDto) {
        buildGame = new BuildGame(newGameDto.getWidth(),
                newGameDto.getHeight(),
                newGameDto.getMinesCount());

        return GameInfoDto.of(buildGame.getGameId().toString(),
                buildGame.getSizeField(),
                buildGame.getSizeField(),
                buildGame.getMinesCount(),
                buildGame.getCompleted(),
                buildGame.getCloseFields());
    }

    public GameInfoDto playGame(GameTurnDto gameTurnDto) {
        if (gameTurnDto.getGameId().equals(buildGame.getGameId().toString()) && !buildGame.getCompleted()) {
            buildGame.openCell(gameTurnDto.getCol() - 1, gameTurnDto.getRow() - 1);
            return GameInfoDto.of(buildGame.getGameId().toString(),
                    buildGame.getSizeField(),
                    buildGame.getSizeField(),
                    buildGame.getMinesCount(),
                    buildGame.getCompleted(),
                    buildGame.getCloseFields());
        } else {
            throw new RuntimeException();
        }
    }
}
