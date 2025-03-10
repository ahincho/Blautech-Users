package com.blautech.ecommerce.users.application.services;

import com.blautech.ecommerce.users.application.ports.in.FindOneUserUseCase;
import com.blautech.ecommerce.users.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.users.domain.models.User;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOneUserDefaultService implements FindOneUserUseCase {
    private final UserPersistencePort userPersistencePort;
    public FindOneUserDefaultService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = this.userPersistencePort.findOneUserById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id '%s' not found", userId));
        }
        return optionalUser.get();
    }
}
