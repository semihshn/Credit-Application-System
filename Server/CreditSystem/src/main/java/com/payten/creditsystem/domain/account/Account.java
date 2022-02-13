package com.payten.creditsystem.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Account {

    private Long id;
    private String mail;
    private String password;

    public void encodePassword(String password) {
        this.password = password;
    }
}
