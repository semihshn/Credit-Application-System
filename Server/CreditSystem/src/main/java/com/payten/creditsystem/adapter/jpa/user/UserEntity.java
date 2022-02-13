package com.payten.creditsystem.adapter.jpa.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.payten.creditsystem.adapter.jpa.common.BaseEntity;
import com.payten.creditsystem.adapter.jpa.common.Status;
import com.payten.creditsystem.adapter.jpa.communication.CommunicationEntity;
import com.payten.creditsystem.adapter.jpa.creditApplicationInformation.CreditApplicationInformationEntity;
import com.payten.creditsystem.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
@Table(name = "users")
@Where(clause = "status <> 'DELETED'")
public class UserEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String firstName;
    private String lastName;
    private String identificationNumber;
    private Long monthlyIncome;

    @OneToMany(mappedBy = "user")
    private List<CommunicationEntity> communicationEntities;

    @OneToMany(mappedBy = "user")
    private List<CreditApplicationInformationEntity> creditApplicationInformationEntities;

    public static UserEntity from(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getId();
        entity.status=Status.ACTIVE;
        entity.firstName = user.getFirstName();
        entity.lastName = user.getLastName();
        entity.identificationNumber=user.getIdentificationNumber();
        entity.monthlyIncome=user.getMonthlyIncome();
        entity.createdDate=user.getCreatedDate();
        return entity;
    }

    public static User toModel(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.id)
                .firstName(userEntity.firstName)
                .lastName(userEntity.lastName)
                .identificationNumber(userEntity.identificationNumber)
                .monthlyIncome(userEntity.monthlyIncome)
                .createdDate(userEntity.createdDate)
                .build();
    }
}
