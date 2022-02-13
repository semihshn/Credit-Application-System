package com.payten.creditsystem.adapter.jpa.creditApplicationInformation;

import com.payten.creditsystem.adapter.jpa.common.BaseEntity;
import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "credit_application_informations")
@Table(name = "credit_application_informations")
public class CreditApplicationInformationEntity extends BaseEntity {

    private Long creditLimit;
    private Boolean creditAcceptanceStatus;

    @ManyToOne
    private UserEntity user;

    public static CreditApplicationInformationEntity from(CreditApplicationInformation creditApplicationInformation, UserEntity user) {
        CreditApplicationInformationEntity entity = new CreditApplicationInformationEntity();
        entity.id = creditApplicationInformation.getId();
        entity.creditLimit=creditApplicationInformation.getCreditLimit();
        entity.creditAcceptanceStatus = creditApplicationInformation.getCreditAcceptanceStatus();
        entity.user=user;
        return entity;
    }

    public static CreditApplicationInformation toModel(CreditApplicationInformationEntity creditApplicationInformationEntity) {
        return CreditApplicationInformation.builder()
                .id(creditApplicationInformationEntity.id)
                .creditLimit(creditApplicationInformationEntity.creditLimit)
                .creditAcceptanceStatus(creditApplicationInformationEntity.creditAcceptanceStatus)
                .userId(creditApplicationInformationEntity.getUser().getId())
                .build();
    }
}
