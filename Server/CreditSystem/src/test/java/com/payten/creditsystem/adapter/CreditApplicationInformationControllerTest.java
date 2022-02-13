package com.payten.creditsystem.adapter;

import com.payten.creditsystem.BaseIntegrationTest;
import com.payten.creditsystem.adapter.jpa.creditApplicationInformation.CreditApplicationInformationEntity;
import com.payten.creditsystem.adapter.jpa.creditApplicationInformation.CreditApplicationInformationJpaRepository;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.request.CreditApplicationInformationCreateRequest;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.response.CreditApplicationInformationCreateResponse;
import com.payten.creditsystem.adapter.rest.creditApplicationInformation.response.CreditApplicationInformationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CreditApplicationInformationControllerTest extends BaseIntegrationTest {

    @Autowired
    CreditApplicationInformationJpaRepository creditApplicationInformationJpaRepository;

    @Test
    @Sql(scripts = "/user-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_create_credit_application_information() {
        //given
        CreditApplicationInformationCreateRequest request = new CreditApplicationInformationCreateRequest();
        request.setUserId(1001L);

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<CreditApplicationInformationCreateResponse> response = testRestTemplate.exchange("/creditapplicationinformations", HttpMethod.POST, new HttpEntity<>(request,httpHeaders), CreditApplicationInformationCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate movie
        Optional<CreditApplicationInformationEntity> creditApplicationInformation = creditApplicationInformationJpaRepository.findById(response.getBody().getId());
        assertThat(creditApplicationInformation).isPresent();
        assertThat(creditApplicationInformation.get().getCreditLimit()).isNotNull();
        assertThat(creditApplicationInformation.get().getCreditAcceptanceStatus()).isNotNull();
        assertThat(creditApplicationInformation.get().getUser().getId()).isEqualTo(1001L);
        assertThat(creditApplicationInformation.get().getCreatedDate()).isNotNull();
    }

    @Test
    @Sql(scripts = "/credit-application-information-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_credit_application_information() {
        // given
        // via Sql Annotation

        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<CreditApplicationInformationResponse[]> response = testRestTemplate.exchange("/creditapplicationinformations", HttpMethod.GET, new HttpEntity<>(httpHeaders), CreditApplicationInformationResponse[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Arrays.stream(response.getBody()).toList())
                .hasSize(4)
                .extracting("id")
                .containsExactly(1001L, 1002L, 1003L, 1004L);
    }

    @Test
    @Sql(scripts = "/credit-application-information-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_credit_application_information_by_identification_number() {
        // given
        // via Sql Annotation

        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<CreditApplicationInformationResponse[]> response = testRestTemplate.exchange("/creditapplicationinformations/11111111111", HttpMethod.GET, new HttpEntity<>(httpHeaders), CreditApplicationInformationResponse[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Arrays.stream(response.getBody()).toList())
                .hasSize(2)
                .extracting("id")
                .containsExactly(1002L, 1004L);
    }
}
