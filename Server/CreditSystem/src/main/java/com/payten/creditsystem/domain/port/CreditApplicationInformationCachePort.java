package com.payten.creditsystem.domain.port;

import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.user.User;

import java.util.Optional;

public interface CreditApplicationInformationCachePort {

    Optional<CreditApplicationInformation> retrieveCreditApplicationInformation(Long creditApplicationInformationId);

    void createCreditApplicationInformation(CreditApplicationInformation creditApplicationInformation);

}
