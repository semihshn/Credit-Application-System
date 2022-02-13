package com.payten.creditsystem.adapter.rest.communication;

import com.payten.creditsystem.adapter.rest.communication.request.CommunicationCreateRequest;
import com.payten.creditsystem.adapter.rest.communication.response.CommunicationCreateResponse;
import com.payten.creditsystem.adapter.rest.communication.response.CommunicationResponse;
import com.payten.creditsystem.domain.communication.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    @PostMapping("/communications")
    @ResponseStatus(HttpStatus.CREATED)
    public CommunicationCreateResponse create(@RequestBody @Valid CommunicationCreateRequest request) {
        Long createdCommunicationId = communicationService.create(request.convertToCommunication());
        return CommunicationCreateResponse.builder().id(createdCommunicationId).build();
    }

    @GetMapping("/communications")
    public List<CommunicationResponse> retrieve() {
        return communicationService.retrieve()
                .stream()
                .map(CommunicationResponse::from)
                .toList();
    }
}
