package com.payten.creditsystem.adapter.rest.creditApplicationInformation.response;

import com.payten.creditsystem.adapter.rest.user.response.UserResponse;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationInformationResponse {

    private Long id;
    private Long userId;
    private Long creditLimit;
    private Boolean creditAcceptanceStatus;

    public static List<CreditApplicationInformationResponse> from(List<CreditApplicationInformation> creditApplicationInformations) {
        return creditApplicationInformations.stream()
                .map(CreditApplicationInformationResponse::from)
                .toList();
    }

    public static CreditApplicationInformationResponse from(CreditApplicationInformation creditApplicationInformation) {
        return CreditApplicationInformationResponse.builder()
                .id(creditApplicationInformation.getId())
                .creditLimit(creditApplicationInformation.getCreditLimit())
                .creditAcceptanceStatus(creditApplicationInformation.getCreditAcceptanceStatus())
                .userId(creditApplicationInformation.getUserId())
                .build();
    }
}
