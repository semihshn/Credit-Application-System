package com.payten.creditsystem.domain.port;

import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.user.User;

import java.util.List;

public interface CreditApplicationInformationPersistencePort {

    Long create(CreditApplicationInformation creditApplicationInformation, User user);

    List<CreditApplicationInformation> retrieve();

    CreditApplicationInformation retrieve(Long id);

    List<CreditApplicationInformation> retrieveByIdentificationNumber(String identificationNumber);
}
