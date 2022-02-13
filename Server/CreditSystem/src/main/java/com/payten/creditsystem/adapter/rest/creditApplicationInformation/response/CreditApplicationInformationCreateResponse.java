package com.payten.creditsystem.adapter.rest.creditApplicationInformation.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationInformationCreateResponse {

    private Long id;
    private Long creditLimit;
    private Boolean creditAcceptanceStatus;
}
