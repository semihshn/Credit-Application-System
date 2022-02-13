package com.payten.creditsystem.adapter.rest.communication.response;

import com.payten.creditsystem.domain.communication.Communication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationResponse {

    private Long id;
    private String type;
    private String address;

    public static List<CommunicationResponse> from(List<Communication> communications) {
        return communications.stream()
                .map(CommunicationResponse::from)
                .toList();
    }

    public static CommunicationResponse from(Communication communication) {
        return CommunicationResponse.builder()
                .id(communication.getId())
                .type(communication.getType())
                .address(communication.getAddress())
                .build();
    }
}
