package com.whiteskittles.minesweeper.http.handler;

import com.whiteskittles.minesweeper.dto.ErrorDto;
import com.whiteskittles.minesweeper.exceptions.EndGameException;
import com.whiteskittles.minesweeper.exceptions.SessionException;
import com.whiteskittles.minesweeper.exceptions.TableSizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice()
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.of("Произошла непредвиденная ошибка"));
    }

    @ExceptionHandler(EndGameException.class)
    public ResponseEntity<?> endGameException(EndGameException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.of("Игра закончена"));
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<?> sessionException(SessionException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.of("Неизвестная сессия"));
    }

    @ExceptionHandler(TableSizeException.class)
    public ResponseEntity<?> tableSizeException(TableSizeException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.of("Количество колонок и столбцов не равны"));
    }
}
