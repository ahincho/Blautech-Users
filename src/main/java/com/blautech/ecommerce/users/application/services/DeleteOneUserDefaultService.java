package com.blautech.ecommerce.users.application.services;

import com.blautech.ecommerce.users.application.ports.in.DeleteOneUserUseCase;
import com.blautech.ecommerce.users.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class DeleteOneUserDefaultService implements DeleteOneUserUseCase {
    private final UserPersistencePort userPersistencePort;
    public DeleteOneUserDefaultService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void execute(Long userId) throws UserNotFoundException {
        if (!this.userPersistencePort.existsOneUserById(userId)) {
            throw new UserNotFoundException(String.format("User with id '%s' not found", userId));
        }
        this.userPersistencePort.deleteOneUserById(userId);
    }
}
