package com.payten.creditsystem.domain.exception;

import lombok.Getter;

@Getter
public class PaytenAuthenticationException extends RuntimeException {

    private final ExceptionType exceptionType;
    private String detail;

    public PaytenAuthenticationException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public PaytenAuthenticationException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
