package com.payten.creditsystem.domain.port;

import com.payten.creditsystem.domain.communication.Communication;
import com.payten.creditsystem.domain.user.User;

import java.util.List;

public interface CommunicationPersistencePort {

    Long create(Communication communication, User user);

    List<Communication> retrieve();
}
