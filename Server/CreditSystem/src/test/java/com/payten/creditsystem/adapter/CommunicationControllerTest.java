package com.payten.creditsystem.adapter;

import com.payten.creditsystem.BaseIntegrationTest;
import com.payten.creditsystem.adapter.jpa.communication.CommunicationEntity;
import com.payten.creditsystem.adapter.jpa.communication.CommunicationJpaRepostity;
import com.payten.creditsystem.adapter.rest.communication.request.CommunicationCreateRequest;
import com.payten.creditsystem.adapter.rest.communication.response.CommunicationCreateResponse;
import com.payten.creditsystem.adapter.rest.communication.response.CommunicationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CommunicationControllerTest extends BaseIntegrationTest {

    @Autowired
    CommunicationJpaRepostity communicationJpaRepository;

    @Test
    @Sql(scripts = "/user-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_create_communication() {
        //given
        CommunicationCreateRequest request = new CommunicationCreateRequest();
        request.setType("test type");
        request.setAddress("test address");
        request.setUserId(1001L);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<CommunicationCreateResponse> response = testRestTemplate.exchange("/communications", HttpMethod.POST, new HttpEntity<>(request,httpHeaders), CommunicationCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate movie
        Optional<CommunicationEntity> communication = communicationJpaRepository.findById(response.getBody().getId());
        assertThat(communication).isPresent();
        assertThat(communication.get().getType()).isEqualTo("test type");
        assertThat(communication.get().getCreatedDate()).isNotNull();
        assertThat(communication.get().getUser().getId()).isNotNull();
    }

    @Test
    @Sql(scripts = "/communication-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_communication() {
        // given
        // via Sql Annotation

        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<CommunicationResponse[]> response = testRestTemplate.exchange("/communications", HttpMethod.GET, new HttpEntity<>(httpHeaders), CommunicationResponse[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Arrays.stream(response.getBody()).toList())
                .hasSize(3)
                .extracting("id")
                .containsExactly(1001L, 1002L, 1003L);
    }
}
