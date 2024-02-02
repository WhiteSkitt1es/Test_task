package com.whiteskittles.minesweeper.service;

import com.whiteskittles.minesweeper.dto.GameInfoDto;
import com.whiteskittles.minesweeper.dto.GameTurnDto;
import com.whiteskittles.minesweeper.dto.NewGameDto;
import com.whiteskittles.minesweeper.exceptions.EndGameException;
import com.whiteskittles.minesweeper.exceptions.SessionException;
import com.whiteskittles.minesweeper.game.BuildGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final Map<UUID, BuildGame> map = new HashMap<>(10);

    public GameInfoDto startGame(NewGameDto newGameDto) {
        BuildGame buildGame = new BuildGame(newGameDto.getWidth(),
                newGameDto.getHeight(),
                newGameDto.getMinesCount());
        map.put(buildGame.getGameId(), buildGame);

        return GameInfoDto.of(buildGame.getGameId().toString(),
                buildGame.getSizeField(),
                buildGame.getSizeField(),
                buildGame.getMinesCount(),
                buildGame.getCompleted(),
                buildGame.getCloseFields());
    }

    public GameInfoDto playGame(GameTurnDto gameTurnDto) {
        if (map.containsKey(UUID.fromString(gameTurnDto.getGameId()))){
            BuildGame game = map.get(UUID.fromString(gameTurnDto.getGameId()));
            if (!game.getCompleted()) {
                game.openCell(gameTurnDto.getCol() - 1, gameTurnDto.getRow() - 1);
                return GameInfoDto.of(game.getGameId().toString(),
                        game.getSizeField(),
                        game.getSizeField(),
                        game.getMinesCount(),
                        game.getCompleted(),
                        game.getCloseFields());
            } else {
                map.remove(game.getGameId());
                throw new EndGameException("Игра закончена");
            }
        } else {
            throw new SessionException("Идентификатор сессии не соответствует действительному");
        }
    }
}
