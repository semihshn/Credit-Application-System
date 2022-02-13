package com.payten.creditsystem.adapter.jpa.user;

import com.payten.creditsystem.adapter.jpa.common.Status;
import com.payten.creditsystem.adapter.jpa.creditApplicationInformation.CreditApplicationInformationEntity;
import com.payten.creditsystem.domain.creditApplicationInformation.CreditApplicationInformation;
import com.payten.creditsystem.domain.exception.ExceptionType;
import com.payten.creditsystem.domain.exception.PaytenDataNotFoundException;
import com.payten.creditsystem.domain.port.UserPersistencePort;
import com.payten.creditsystem.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Long create(User user) {
        UserEntity createdUser = userJpaRepository.save(UserEntity.from(user));
        return createdUser.getId();
    }

    @Override
    public List<User> retrieve() {
        return userJpaRepository.findAll()
                .stream()
                .map(UserEntity::toModel)
                .toList();
    }

    @Override
    public UserEntity retrieve(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(()->new PaytenDataNotFoundException(ExceptionType.USER_DATA_NOT_FOUND));
    }

    @Override
    public User retrieveByIdentificationNumber(String identificationNumber) {
        Optional<UserEntity> entityOptional =userJpaRepository.findByIdentificationNumber(identificationNumber);
        if(entityOptional.isPresent())
            return UserEntity.toModel(entityOptional.get());
        return null;
    }

    @Override
    public void delete(Long id) {
        userJpaRepository.findById(id)
                .ifPresent(user -> {
                    user.setStatus(Status.DELETED);
                    userJpaRepository.save(user);
                });
    }

    @Override
    public Long update(User user) {
        UserEntity entity=UserEntity.from(user);
        userJpaRepository.save(entity);
        return user.getId();
    }
}
