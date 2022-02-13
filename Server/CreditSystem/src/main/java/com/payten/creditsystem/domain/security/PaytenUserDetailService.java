package com.payten.creditsystem.domain.security;

import com.payten.creditsystem.domain.account.Account;
import com.payten.creditsystem.domain.port.AccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaytenUserDetailService implements UserDetailsService {

    private final AccountPort accountPort;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Account appAccount = accountPort.retrieveByMail(mail);
        return new User(appAccount.getMail(), appAccount.getPassword(), List.of());
    }
}
