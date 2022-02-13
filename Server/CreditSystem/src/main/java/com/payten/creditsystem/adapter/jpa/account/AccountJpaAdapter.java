package com.payten.creditsystem.adapter.jpa.account;

import com.payten.creditsystem.domain.account.Account;
import com.payten.creditsystem.domain.exception.ExceptionType;
import com.payten.creditsystem.domain.exception.PaytenDataNotFoundException;
import com.payten.creditsystem.domain.port.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountJpaAdapter implements AccountPort {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account retrieveByMail(String mail) {
        return accountJpaRepository.findByMail(mail)
                .orElseThrow(() -> new PaytenDataNotFoundException(ExceptionType.MAIL_NOT_FOUND))
                .toModel();
    }

    @Override
    public Account create(Account account) {
        AccountEntity accountEntity = AccountEntity.from(account);
        return accountJpaRepository.save(accountEntity).toModel();
    }

    @Override
    public Account retrieve(Long id) {
        return retrieveUserEntity(id)
                .toModel();
    }

    @Override
    public Boolean isMailExists(String mail) {
        return accountJpaRepository.existsByMail(mail);
    }

    private AccountEntity retrieveUserEntity(Long id) {
        return accountJpaRepository.findById(id)
                .orElseThrow(() -> new PaytenDataNotFoundException(ExceptionType.ACCOUNT_NOT_FOUND));
    }

}
