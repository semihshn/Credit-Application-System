package com.payten.creditsystem.adapter.rest.creditApplicationInformation.request;

import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreditApplicationInformationCreateRequest {

    @NotNull
    private Long userId;

    public CreditApplicationInformation convertToCreditApplicationInformation() {
        return CreditApplicationInformation.builder()
                .userId(userId)
                .build();
    }
}
