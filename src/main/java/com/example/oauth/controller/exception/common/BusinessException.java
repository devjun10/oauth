package com.example.oauth.controller.exception.common;

public class BusinessException extends RuntimeException {

    private final BaseExceptionType baseExceptionType;

    public BusinessException(BaseExceptionType baseExceptionType) {
        super(baseExceptionType.getMessage());
        this.baseExceptionType = baseExceptionType;
    }

    public BaseExceptionType getBaseExceptionType() {
        return baseExceptionType;
    }
}
