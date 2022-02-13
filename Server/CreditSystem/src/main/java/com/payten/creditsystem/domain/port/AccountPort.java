package com.payten.creditsystem.domain.port;

import com.payten.creditsystem.domain.account.Account;

public interface AccountPort {

    Account retrieveByMail(String mail);

    Account create(Account account);

    Account retrieve(Long id);

    Boolean isMailExists(String mail);
}
