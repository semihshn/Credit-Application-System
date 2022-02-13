package com.payten.creditsystem.domain.businessEngine.result;

import com.payten.creditsystem.domain.exception.ExceptionType;

public class ErrorResult extends Result {

    public ErrorResult(ExceptionType exceptionType,String message) {
        super(false, message, exceptionType);
    }

    public ErrorResult(ExceptionType exceptionType) {
        super(false, exceptionType);
    }
}
