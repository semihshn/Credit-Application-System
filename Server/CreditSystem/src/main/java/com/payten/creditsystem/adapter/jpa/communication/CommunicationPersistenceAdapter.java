package com.payten.creditsystem.adapter.jpa.communication;

import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.communication.Communication;
import com.payten.creditsystem.domain.port.CommunicationPersistencePort;
import com.payten.creditsystem.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationPersistenceAdapter implements CommunicationPersistencePort {

    private final CommunicationJpaRepostity communicationJpaRepostity;

    @Override
    public Long create(Communication communication, User user) {
        UserEntity userEntity=UserEntity.from(user);
        CommunicationEntity createdCommunication = communicationJpaRepostity.save(CommunicationEntity.from(communication,userEntity));
        return createdCommunication.getId();
    }

    @Override
    public List<Communication> retrieve() {
        return communicationJpaRepostity.findAll()
                .stream()
                .map(CommunicationEntity::toModel)
                .toList();
    }
}
