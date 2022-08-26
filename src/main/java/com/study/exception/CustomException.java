package com.study.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{ //내가 커스텀 예외처리하는 용도

    private final ErrorCode errorCode;
}
