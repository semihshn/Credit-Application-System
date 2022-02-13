package com.payten.creditsystem.domain.communication;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Communication {

    private Long id;
    private Long userId;
    private String type;
    private String address;
}
