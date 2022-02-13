package com.payten.creditsystem.adapter.redis;

import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationInformationCache {

    private Long id;
    private Long creditLimit;
    private Boolean creditAcceptanceStatus;

    public static CreditApplicationInformationCache from(CreditApplicationInformation creditApplicationInformation) {
        return CreditApplicationInformationCache.builder()
                .id(creditApplicationInformation.getId())
                .creditLimit(creditApplicationInformation.getCreditLimit())
                .creditAcceptanceStatus(creditApplicationInformation.getCreditAcceptanceStatus())
                .build();
    }

    public CreditApplicationInformation toModel() {
        return CreditApplicationInformation.builder()
                .id(id)
                .creditLimit(creditLimit)
                .creditAcceptanceStatus(creditAcceptanceStatus)
                .build();
    }

}
