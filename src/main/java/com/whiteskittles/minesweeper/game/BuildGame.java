package com.whiteskittles.minesweeper.game;

import com.whiteskittles.minesweeper.exceptions.EndGameException;
import com.whiteskittles.minesweeper.exceptions.TableSizeException;
import lombok.Getter;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Getter
public class BuildGame {

    private final UUID gameId = UUID.randomUUID();
    private final Integer sizeField;
    private final Integer minesCount;
    private Boolean completed;
    private String[][] openFields;
    private String[][] closeFields;
    private int countOpenedCells;

    public BuildGame(Integer width, Integer height, Integer minesCount) {
        if (Objects.equals(width, height)) {
            this.sizeField = width;
        } else {
            throw new TableSizeException("Высота и ширина поля не равны!");
        }
        this.minesCount = minesCount;
        this.completed = false;
        this.countOpenedCells = 0;
        createFields();
    }

    private void createFields() {
        int x, y, numberOfMines = 0;
        Random random = new Random();

        openFields = new String[sizeField][sizeField];
        closeFields = new String[sizeField][sizeField];
        for (y = 0; y < sizeField; y++) {
            for (x = 0; x < sizeField; x++) {
                openFields[y][x] = " ";
                closeFields[y][x] = " ";
            }
        }

        while (numberOfMines < minesCount) {
            do {
                x = random.nextInt(minesCount);
                y = random.nextInt(minesCount);
            } while (Objects.equals(openFields[y][x], "X"));
            openFields[y][x] = "X";
            numberOfMines++;
        }

        for (x = 0; x < sizeField; x++) {
            for (y = 0; y < sizeField; y++) {
                if (!isFieldMine(y, x)) {
                    int count = 0;
                    for (int dx = -1; dx < 2; dx++) {
                        for (int dy = -1; dy < 2; dy++) {
                            int nX = x + dx;
                            int nY = y + dy;
                            if (nX < 0 || nY < 0 || nX > sizeField - 1 || nY > sizeField - 1) {
                                nX = x;
                                nY = y;
                            }
                            count += (isFieldMine(nY, nX)) ? 1 : 0;
                        }
                    }
                    openFields[y][x] = String.valueOf(count);
                }
            }
        }
    }

    private boolean isFieldMine(int y, int x) {
        return Objects.equals(openFields[y][x], "X");
    }

    private boolean isNotOpen(int y, int x) {
        return Objects.equals(closeFields[y][x], " ");
    }

    public void openCell(int y, int x) {
        if (countOpenedCells < sizeField * sizeField - minesCount) {
            if (isNotOpen(y, x)) {
//                closeFields[y][x] = openFields[y][x];
                openCells(x, y);
//                countOpenedCells++;
            }
            if (isFieldMine(y, x)) {
                closeFields[y][x] = openFields[y][x];
                completed = true;
                closeFields = openFields;
            }
        } else {
            for (x = 0; x < sizeField; x++) {
                for (y = 0; y < sizeField; y++) {
                    if (isFieldMine(y, x)) {
                        openFields[y][x] = "M";
                    }
                }
            }
            completed = true;
            closeFields = openFields;
        }
    }

    void openCells(int x, int y) {
        if (x < 0 || x > sizeField - 1 || y < 0 || y > sizeField - 1) return;
        if (!isNotOpen(y, x)) return;
        closeFields[y][x] = openFields[y][x];
        countOpenedCells++;
        if (isFieldMine(y, x)) return;
        if (Integer.parseInt(openFields[y][x]) > 0) return;
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++) openCells(x + dx, y + dy);
    }
}
