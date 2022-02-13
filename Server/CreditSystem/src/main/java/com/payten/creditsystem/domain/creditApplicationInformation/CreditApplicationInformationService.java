package com.payten.creditsystem.domain.creditApplicationInformation;

import com.payten.creditsystem.adapter.externalService.CreditScoreService;
import com.payten.creditsystem.adapter.externalService.MessageService;
import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.businessEngine.result.Result;
import com.payten.creditsystem.domain.port.CreditApplicationInformationCachePort;
import com.payten.creditsystem.domain.port.CreditApplicationInformationPersistencePort;
import com.payten.creditsystem.domain.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditApplicationInformationService {

    private final CreditApplicationInformationPersistencePort creditApplicationInformationPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final CreditApplicationInformationCachePort creditApplicationInformationCachePort;

    public Long create(CreditApplicationInformation creditApplicationInformation) {

        // get user's credit score
        UserEntity entity=userPersistencePort.retrieve(creditApplicationInformation.getUserId());
        Integer creditScore= CreditScoreService.retrieveCreditScore(entity.getIdentificationNumber());

        Long creditLimit=getCreditLimitByConditions(creditScore,entity.getMonthlyIncome());

        if(checkIfCreditScore(creditScore) || creditLimit==0){
            creditApplicationInformation.setCreditAcceptanceStatus(false);
        }else{
            creditApplicationInformation.setCreditAcceptanceStatus(true);
        }

        creditApplicationInformation.setCreditLimit(creditLimit);

        Long createdId=creditApplicationInformationPersistencePort.create(creditApplicationInformation, UserEntity.toModel(entity));
        MessageService.sendMessage();
        return createdId;
    }

    public List<CreditApplicationInformation> retrieve() {
        return creditApplicationInformationPersistencePort.retrieve();
    }

    public CreditApplicationInformation retrieve(Long id) {
        Optional<CreditApplicationInformation> creditApplicationInformation = creditApplicationInformationCachePort.retrieveCreditApplicationInformation(id);
        log.info("Credit application information is retrieving: {}", id);

        if (creditApplicationInformation.isEmpty()) {
            log.info("Credit application information cache is updating: {}", id);
            CreditApplicationInformation retrievedCreditApplicationInformation = creditApplicationInformationPersistencePort.retrieve(id);
            creditApplicationInformationCachePort.createCreditApplicationInformation(retrievedCreditApplicationInformation);
            return retrievedCreditApplicationInformation;
        }

        return creditApplicationInformation.get();
    }

    public List<CreditApplicationInformation> retrieveByIdentificationNumber(String identificationNumber) {
        return creditApplicationInformationPersistencePort
                .retrieveByIdentificationNumber(identificationNumber);
    }

    private boolean checkIfCreditScore(Integer score) {
        if(score<500)
            return true;

        return false;
    }

    private Long getCreditLimitByConditions(Integer score, Long monthlyIncome) {
        if(score>500 && score<1000){
            if(monthlyIncome<5000)
                return 10000L;
            if (monthlyIncome>5000)
                return 20000L;
        }else if(score>=1000){
            return monthlyIncome*4;
        }
    return 0L;
    }
}
