package com.payten.creditsystem.adapter.rest.user.request;

import com.payten.creditsystem.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String identificationNumber;

    @NotNull
    private Long monthlyIncome;

    @NotNull
    private LocalDateTime createdDate;

    public User convertToUser() {
        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .identificationNumber(identificationNumber)
                .monthlyIncome(monthlyIncome)
                .createdDate(createdDate)
                .build();
    }
}
