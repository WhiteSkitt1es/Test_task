package com.whiteskittles.minesweeper;

import com.whiteskittles.minesweeper.game.BuildGame;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class MinesweeperApplicationTests {

    @Test
    void contextLoads() {
        BuildGame game = new BuildGame(10, 10, 10);
        String[][] fields = game.getOpenFields();
        System.out.println(Arrays.deepToString(fields));
        System.out.println(game.getGameId());

    }

}
