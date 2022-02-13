package com.payten.creditsystem.domain.user;

import com.payten.creditsystem.domain.businessEngine.BusinessRules;
import com.payten.creditsystem.domain.businessEngine.result.ErrorResult;
import com.payten.creditsystem.domain.businessEngine.result.Result;
import com.payten.creditsystem.domain.businessEngine.result.SuccessResult;
import com.payten.creditsystem.domain.exception.ExceptionType;
import com.payten.creditsystem.domain.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersistencePort userPersistencePort;

    public Long create(User user) {

        // Check if business rule CLEAN CODE, LOW COMPLEXITY
        BusinessRules.run(
                checkIfIdentificationNumberExists(user.getIdentificationNumber())
        );
        return userPersistencePort.create(user);
    }

    public List<User> retrieve() {
        return userPersistencePort.retrieve();
    }

    public void delete(Long id) {
        userPersistencePort.delete(id);
    }

    public Long update(User user) {
        return userPersistencePort.update(user);
    }

    private Result checkIfIdentificationNumberExists(String identificationNumber){
        User user=userPersistencePort.retrieveByIdentificationNumber(identificationNumber);
        if(user!=null){
            return new ErrorResult(ExceptionType.IDENTIFICATION_NUMBER_EXISTS,"Hata");
        }

        return new SuccessResult();
    }
}
