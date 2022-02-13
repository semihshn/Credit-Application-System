package com.payten.creditsystem.domain.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String identificationNumber;
    private Long monthlyIncome;
    private LocalDateTime createdDate;

}
