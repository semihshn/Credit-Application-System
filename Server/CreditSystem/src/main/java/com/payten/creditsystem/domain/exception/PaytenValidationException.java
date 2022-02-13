package com.payten.creditsystem.domain.exception;

import lombok.Getter;

@Getter
public class PaytenValidationException extends RuntimeException {

    private final ExceptionType exceptionType;
    private String detail;

    public PaytenValidationException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public PaytenValidationException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
