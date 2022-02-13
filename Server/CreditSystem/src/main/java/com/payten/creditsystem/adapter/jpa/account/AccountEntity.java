package com.payten.creditsystem.adapter.jpa.account;

import com.payten.creditsystem.adapter.jpa.common.BaseEntity;
import com.payten.creditsystem.domain.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "account")
@Table(name = "account")
public class AccountEntity extends BaseEntity {

    private String mail;
    private String password;

    public static AccountEntity from(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.password = account.getPassword();
        accountEntity.mail = account.getMail();
        return accountEntity;
    }

    public Account toModel() {
        return Account.builder()
                .id(id)
                .mail(mail)
                .password(password)
                .build();
    }
}
