package com.payten.creditsystem.adapter.rest.user.request;

import com.payten.creditsystem.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String identificationNumber;

    @NotNull
    private Long monthlyIncome;

    public User convertToUser() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .identificationNumber(identificationNumber)
                .monthlyIncome(monthlyIncome)
                .build();
    }
}
