package com.payten.creditsystem.domain.businessEngine;

import com.payten.creditsystem.domain.exception.PaytenBusinessRuleException;
import com.payten.creditsystem.domain.businessEngine.result.Result;

public class BusinessRules {

    public static void run(Result... logics) {
        for (Result logic : logics)
            if (!logic.isSuccess()){
                throw new PaytenBusinessRuleException(logic.getExceptionType(),logic.getMessage());}
    }
}
