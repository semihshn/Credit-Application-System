package com.payten.creditsystem.adapter.rest.communication.request;

import com.payten.creditsystem.domain.communication.Communication;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommunicationCreateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String type;

    @NotBlank
    private String address;

    public Communication convertToCommunication() {
        return Communication.builder()
                .type(type)
                .address(address)
                .userId(userId)
                .build();
    }

}
