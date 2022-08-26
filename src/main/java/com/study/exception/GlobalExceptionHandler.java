package com.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j //해당 어노테이션이 선언된 클래스에 자동으로 로그 객체 생성
public class GlobalExceptionHandler { //전역 예외 핸들링용 핸들러 생성

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("handlerCustomException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

//    @ExceptionHandler(RuntimeException.class)
//    public String handleRuntimeException(final RuntimeException e){
//        log.error("handleRuntimeException : {}", e.getMessage());
//        return e.getMessage();
//    }
}
