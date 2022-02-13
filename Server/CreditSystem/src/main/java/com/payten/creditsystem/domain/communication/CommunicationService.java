package com.payten.creditsystem.domain.communication;

import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.port.CommunicationPersistencePort;
import com.payten.creditsystem.domain.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationPersistencePort communicationPersistencePort;
    private final UserPersistencePort userPersistencePort;

    public Long create(Communication communication) {
        UserEntity entity=userPersistencePort.retrieve(communication.getUserId());
        return communicationPersistencePort.create(communication,UserEntity.toModel(entity));
    }

    public List<Communication> retrieve() {
        return communicationPersistencePort.retrieve();
    }
}
