package com.payten.creditsystem.domain.businessEngine.result;

import com.payten.creditsystem.domain.exception.ExceptionType;

public class Result {

    private Boolean success;
    private String message;
    private ExceptionType exceptionType;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(Boolean success,String message, ExceptionType exceptionType) {
        this(success);
        this.message = message;
        this.exceptionType=exceptionType;
    }

    public Result(Boolean success, ExceptionType exceptionType) {
        this(success);
        this.message = message;
        this.exceptionType=exceptionType;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public ExceptionType getExceptionType() {
        return this.exceptionType;
    }
}
