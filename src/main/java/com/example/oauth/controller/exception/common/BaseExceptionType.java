package com.example.oauth.controller.exception.common;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    int getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
