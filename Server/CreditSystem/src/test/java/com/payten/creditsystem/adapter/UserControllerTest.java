package com.payten.creditsystem.adapter;

import com.payten.creditsystem.BaseIntegrationTest;
import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.adapter.jpa.user.UserJpaRepository;
import com.payten.creditsystem.adapter.rest.user.request.UserCreateRequest;
import com.payten.creditsystem.adapter.rest.user.request.UserUpdateRequest;
import com.payten.creditsystem.adapter.rest.user.response.UserCreateResponse;
import com.payten.creditsystem.adapter.rest.user.response.UserResponse;
import com.payten.creditsystem.adapter.rest.user.response.UserUpdateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerTest extends BaseIntegrationTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_create_user() {
        //given
        UserCreateRequest request = new UserCreateRequest();
        request.setFirstName("test first name");
        request.setLastName("test last name");
        request.setMonthlyIncome(2600L);
        request.setIdentificationNumber("12345678911");

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<UserCreateResponse> response = testRestTemplate.exchange("/users", HttpMethod.POST, new HttpEntity<>(request,httpHeaders), UserCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate movie
        Optional<UserEntity> user = userJpaRepository.findById(response.getBody().getId());
        assertThat(user).isPresent();
        assertThat(user.get().getFirstName()).isEqualTo("test first name");
        assertThat(user.get().getCreatedDate()).isNotNull();
    }

    @Test
    @Sql(scripts = "/user-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_user() {
        // given
        // via Sql Annotation

        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        ResponseEntity<UserResponse[]> response = testRestTemplate.exchange("/users", HttpMethod.GET, new HttpEntity<>(httpHeaders), UserResponse[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Arrays.stream(response.getBody()).toList())
                .hasSize(3)
                .extracting("id")
                .containsExactly(1001L, 1002L, 1003L);
    }

    @Test
    @Sql(scripts = "/user-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_delete_user() {
        //given
        Optional<UserEntity> optionalUser = userJpaRepository.findById(1001L);
        assertThat(optionalUser).isPresent();

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        testRestTemplate.exchange("/users/1001", HttpMethod.DELETE, new HttpEntity<>(httpHeaders), Void.class);

        //then
        optionalUser = userJpaRepository.findById(1001L);
        assertThat(optionalUser).isEmpty();
    }

    @Test
    @Sql(scripts = "/user-create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_update_user() {
        //given
        Optional<UserEntity> optionalUser = userJpaRepository.findById(1001L);
        assertThat(optionalUser).isPresent();

        UserEntity entity= optionalUser.get();

        UserUpdateRequest request = new UserUpdateRequest();
        request.setId(entity.getId());
        request.setFirstName("Melike");
        request.setLastName(entity.getLastName());
        request.setMonthlyIncome(entity.getMonthlyIncome());
        request.setIdentificationNumber(entity.getIdentificationNumber());
        request.setCreatedDate(LocalDateTime.now());

        //when
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, BEARER_TOKEN);

        testRestTemplate.exchange("/users", HttpMethod.PUT, new HttpEntity<>(request,httpHeaders), UserUpdateResponse.class);

        //then
        optionalUser = userJpaRepository.findById(1001L);
        assertThat(optionalUser.get().getFirstName()).isEqualTo("Melike");
    }

}
