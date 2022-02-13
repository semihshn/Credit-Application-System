package com.payten.creditsystem.adapter.rest.creditApplicationInformation;

import com.payten.creditsystem.adapter.jpa.creditApplicationInformation.CreditApplicationInformationEntity;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.request.CreditApplicationInformationCreateRequest;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.response.CreditApplicationInformationCreateResponse;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.response.CreditApplicationInformationResponse;
import com.payten.creditsystem.adapter.rest.user.request.UserCreateRequest;
import com.payten.creditsystem.adapter.rest.user.response.UserCreateResponse;
import com.payten.creditsystem.adapter.rest.user.response.UserResponse;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformationService;
import com.payten.creditsystem.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditApplicationInformationController {

    private final CreditApplicationInformationService creditApplicationInformationService;

    @PostMapping("/creditapplicationinformations")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditApplicationInformationResponse create(@RequestBody @Valid CreditApplicationInformationCreateRequest request) {
        Long createdCreditApplicationInformationId = creditApplicationInformationService.create(request.convertToCreditApplicationInformation());
        CreditApplicationInformation creditApplicationInformation=creditApplicationInformationService.retrieve(createdCreditApplicationInformationId);

        return CreditApplicationInformationResponse.builder()
                .id(createdCreditApplicationInformationId)
                .creditLimit(creditApplicationInformation.getCreditLimit())
                .creditAcceptanceStatus(creditApplicationInformation.getCreditAcceptanceStatus())
                .userId(creditApplicationInformation.getUserId())
                .build();
    }

    @GetMapping("/creditapplicationinformations")
    public List<CreditApplicationInformationResponse> retrieve() {
        return creditApplicationInformationService.retrieve()
                .stream()
                .map(CreditApplicationInformationResponse::from)
                .toList();
    }

    @GetMapping("/creditapplicationinformations/{identificationnumber}")
    public List<CreditApplicationInformationResponse> retrieve(@PathVariable String identificationnumber) {
        List<CreditApplicationInformation> creditApplicationInformation = creditApplicationInformationService.retrieveByIdentificationNumber(identificationnumber);
        return creditApplicationInformation
                .stream()
                .map(CreditApplicationInformationResponse::from)
                .toList();
    }
}
