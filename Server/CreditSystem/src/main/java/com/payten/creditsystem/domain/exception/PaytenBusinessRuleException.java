package com.payten.creditsystem.domain.exception;

import lombok.Getter;

@Getter
public class PaytenBusinessRuleException extends RuntimeException {

    private final ExceptionType exceptionType;
    private String detail;

    public PaytenBusinessRuleException(ExceptionType exceptionType, String detail) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.detail = detail;
    }

    public PaytenBusinessRuleException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
