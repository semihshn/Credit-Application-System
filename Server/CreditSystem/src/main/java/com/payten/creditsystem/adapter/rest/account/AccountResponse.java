package com.payten.creditsystem.adapter.rest.account;

import com.payten.creditsystem.domain.account.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountResponse {
    private final Long id;
    private final String mail;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .mail(account.getMail())
                .build();
    }

}
