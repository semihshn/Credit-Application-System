package com.payten.creditsystem.domain.creditApplicationInformation;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreditApplicationInformation {

    private Long id;
    private Long userId;
    private Long creditLimit;
    private Boolean creditAcceptanceStatus;

}
