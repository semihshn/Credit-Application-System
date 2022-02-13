package com.payten.creditsystem.domain.account;

import com.payten.creditsystem.domain.businessEngine.BusinessRules;
import com.payten.creditsystem.domain.businessEngine.result.ErrorResult;
import com.payten.creditsystem.domain.businessEngine.result.Result;
import com.payten.creditsystem.domain.businessEngine.result.SuccessResult;
import com.payten.creditsystem.domain.exception.ExceptionType;
import com.payten.creditsystem.domain.exception.PaytenValidationException;
import com.payten.creditsystem.domain.port.AccountPort;
import com.payten.creditsystem.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountPort accountPort;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account createAccount(Account account) {

        // Check if business rule CLEAN CODE, LOW COMPLEXITY
        BusinessRules.run(
                checkIfEmailExists(account.getMail())
        );

        account.encodePassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountPort.create(account);
    }

    public Account retrieve(Long id) {
        return accountPort.retrieve(id);
    }

    private Result checkIfEmailExists(String mail){

        if(accountPort.isMailExists(mail)){
            return new ErrorResult(ExceptionType.MAIL_EXISTS,"Hata");
        }

        return new SuccessResult();
    }
}
