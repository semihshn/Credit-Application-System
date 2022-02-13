package com.payten.creditsystem.adapter.jpa.creditApplicationInformation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditApplicationInformationJpaRepository extends JpaRepository<CreditApplicationInformationEntity, Long> {

    List<CreditApplicationInformationEntity> findAllByUserIdentificationNumber(String identificationNumber);

}
