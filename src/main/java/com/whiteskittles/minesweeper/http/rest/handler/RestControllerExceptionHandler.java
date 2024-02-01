package com.whiteskittles.minesweeper.http.rest.handler;

import com.whiteskittles.minesweeper.dto.ErrorDto;
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
}
