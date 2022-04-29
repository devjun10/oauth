package com.example.oauth.common.exception;


public class BusinessException extends RuntimeException {

    private final BaseExceptionType baseExceptionType;

    public BusinessException(final BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public BaseExceptionType getBaseExceptionType() {
        return baseExceptionType;
    }
}
