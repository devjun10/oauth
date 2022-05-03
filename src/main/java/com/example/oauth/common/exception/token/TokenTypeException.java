package com.example.oauth.common.exception.token;

import com.example.oauth.common.exception.BaseExceptionType;
import com.example.oauth.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum TokenTypeException implements BaseExceptionType {
    EXPIRED_JWT_EXCEPTION(404, "해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    MALFORMED_JWT_EXCEPTION(404, "해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SIGNATURE_EXCEPTION(404, "해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ILLEGAL_ARGUMENT_EXCEPTION(404, "해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    UNSUPPORTED_JWT_EXCEPTION(404, "해당 유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    TokenTypeException(int errorCode, String message, HttpStatus httpStatus) {
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
