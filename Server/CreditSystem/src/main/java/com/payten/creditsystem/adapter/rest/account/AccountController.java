package com.payten.creditsystem.adapter.rest.account;

import com.payten.creditsystem.domain.account.Account;
import com.payten.creditsystem.domain.account.AccountService;
import com.payten.creditsystem.domain.security.JwtUtil;
import com.payten.creditsystem.domain.security.PaytenUserDetailService;
import com.payten.creditsystem.domain.security.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final PaytenUserDetailService paytenUserDetailService;
    private final JwtUtil jwtUtil;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody SignUpRequest request) {
        Account account = accountService.createAccount(request.toModel());
        return AccountResponse.from(account);
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse retrieve(@PathVariable Long id) {
        Account account = accountService.retrieve(id);
        return AccountResponse.from(account);
    }

    @PostMapping("/login")
    public String createToken(@RequestBody LoginRequest loginRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(loginRequest.getPassword());
        final UserDetails userDetails = paytenUserDetailService.loadUserByUsername(loginRequest.getMail());
        if (userDetails == null)
            throw new BadCredentialsException(loginRequest.getMail());
        boolean isPasswordMatches = passwordEncoder.matches(loginRequest.getPassword(), hashedPassword);
        if (!isPasswordMatches)
            throw new BadCredentialsException(loginRequest.getMail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }
}
