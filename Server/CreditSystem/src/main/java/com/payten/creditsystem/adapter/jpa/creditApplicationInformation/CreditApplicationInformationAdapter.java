package com.payten.creditsystem.adapter.jpa.creditApplicationInformation;

import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.adapter.jpa.user.UserJpaRepository;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.exception.ExceptionType;
import com.payten.creditsystem.domain.exception.PaytenDataNotFoundException;
import com.payten.creditsystem.domain.port.CreditApplicationInformationPersistencePort;
import com.payten.creditsystem.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditApplicationInformationAdapter implements CreditApplicationInformationPersistencePort {

    private final CreditApplicationInformationJpaRepository creditApplicationInformationJpaRepository;

    @Override
    public Long create(CreditApplicationInformation creditApplicationInformation, User user) {
        UserEntity userEntity=UserEntity.from(user);
        CreditApplicationInformationEntity createdCreditApplicationInformation = creditApplicationInformationJpaRepository.save(CreditApplicationInformationEntity.from(creditApplicationInformation,userEntity));
        return createdCreditApplicationInformation.getId();
    }

    @Override
    public List<CreditApplicationInformation> retrieve() {
        return creditApplicationInformationJpaRepository.findAll()
                .stream()
                .map(CreditApplicationInformationEntity::toModel)
                .toList();
    }

    @Override
    public CreditApplicationInformation retrieve(Long id) {
        return CreditApplicationInformationEntity.toModel(creditApplicationInformationJpaRepository.findById(id)
                .orElseThrow(()->new PaytenDataNotFoundException(ExceptionType.CREDIT_APPLICATION_INFORMATION_DATA_NOT_FOUND)));
    }

    @Override
    public List<CreditApplicationInformation> retrieveByIdentificationNumber(String identificationNumber) {
        return creditApplicationInformationJpaRepository
                .findAllByUserIdentificationNumber(identificationNumber)
                        .stream()
                .map(CreditApplicationInformationEntity::toModel)
                .toList();
    }
}
