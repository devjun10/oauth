package com.example.oauth.common.exception.common;

import com.example.oauth.common.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum OauthTypeException implements BaseExceptionType {

    INVALID_OAUTH_PROVIDER_EXCEPTION(404, "", HttpStatus.NOT_FOUND);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    OauthTypeException(int errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
