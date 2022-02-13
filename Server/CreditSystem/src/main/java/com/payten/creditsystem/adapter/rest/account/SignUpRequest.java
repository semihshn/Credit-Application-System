package com.payten.creditsystem.adapter.rest.account;

import com.payten.creditsystem.domain.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignUpRequest {

    @NotEmpty(message = "mail is not null")
    private String mail;

    @NotEmpty(message = "password is not null")
    private String password;

    public Account toModel() {
        return Account.builder()
                .mail(mail)
                .password(password)
                .build();
    }
}
