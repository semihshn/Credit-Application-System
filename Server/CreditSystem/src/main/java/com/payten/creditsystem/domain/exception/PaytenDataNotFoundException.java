package com.payten.creditsystem.domain.exception;

import lombok.Getter;

@Getter
public class PaytenDataNotFoundException extends RuntimeException {

    private final ExceptionType exceptionType;
    private String detail;

    public PaytenDataNotFoundException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public PaytenDataNotFoundException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
