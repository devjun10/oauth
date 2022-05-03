package com.example.oauth.common.configuration.filter;

import com.example.oauth.common.exception.BaseExceptionType;

public class InvalidJwtTokenException extends RuntimeException {
    private final BaseExceptionType baseExceptionType;

    public InvalidJwtTokenException(final BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public BaseExceptionType getBaseExceptionType() {
        return baseExceptionType;
    }

}
