package com.payten.creditsystem.adapter.jpa.communication;

import com.payten.creditsystem.adapter.jpa.common.BaseEntity;
import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.communication.Communication;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "communications")
@Table(name = "communications")
public class CommunicationEntity extends BaseEntity {

    private String type;
    private String address;

    @ManyToOne
    private UserEntity user;

    public static CommunicationEntity from(Communication communication, UserEntity user) {
        CommunicationEntity entity = new CommunicationEntity();
        entity.id = communication.getId();
        entity.type = communication.getType();
        entity.address = communication.getAddress();
        entity.user=user;
        return entity;
    }

    public static Communication toModel(CommunicationEntity communicationEntity) {
        return Communication.builder()
                .id(communicationEntity.id)
                .type(communicationEntity.type)
                .address(communicationEntity.address)
                .build();
    }
}
