package com.payten.creditsystem.domain.port;

import com.payten.creditsystem.adapter.jpa.user.UserEntity;
import com.payten.creditsystem.domain.user.User;

import java.util.List;

public interface UserPersistencePort {

    Long create(User user);

    List<User> retrieve();

    UserEntity retrieve(Long id);

    User retrieveByIdentificationNumber(String identificationNumber);

    void delete(Long id);

    Long update(User user);
}
