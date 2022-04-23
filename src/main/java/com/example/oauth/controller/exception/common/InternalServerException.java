package com.example.oauth.controller.exception.common;

import org.springframework.http.HttpStatus;

public enum InternalServerException implements BaseExceptionType {

    INTERNAL_SERVER_ERROR(500, "서버 내부 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    InternalServerException(int errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
