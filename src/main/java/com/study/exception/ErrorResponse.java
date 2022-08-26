package com.study.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse { //이 클래스는 ErrorCode를 통한 객체 생성만 허용

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.error=errorCode.getStatus().name();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

}
