package com.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j //해당 어노테이션이 선언된 클래스에 자동으로 로그 객체 생성
public class GlobalExceptionHandler { //전역 예외 핸들링용 핸들러 생성

    /*
    개발자 커스텀 익셉션 처리
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        // HTTP Request에 대한 응답 데이터를 포함하는 클래스가 ResponseEntity<T> / <T> 란 Type을 뜻함.
        //<ErrorResponse>의 데이터와 HTTP 상태코드를 함께 리턴할 수 있음.
        //아래는 예외가 발생했을 때, ErrorResponse 형식으로 예외 정보를 ResponseEntity로 내려줌.

        log.error("handleCustomException: {}", e.getErrorCode());
        return ResponseEntity
                .status(e.getErrorCode().getStatus().value())
                .body(new ErrorResponse(e.getErrorCode()));
    }

    /*
    HTTP 405 Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED));
    }

    /*
    HTTP 500 Exception (수정)
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException: {}", e.getMessage());
        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    /*
    HTTP 500 Exception
     */

//    @ExceptionHandler(RuntimeException.class)
//    public String handleRuntimeException(final RuntimeException e){
//        log.error("handleRuntimeException : {}", e.getMessage());
//        return e.getMessage();
//    }
}
