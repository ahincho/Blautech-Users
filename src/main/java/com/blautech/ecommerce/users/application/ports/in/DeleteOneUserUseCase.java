package com.blautech.ecommerce.users.application.ports.in;

import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;

public interface DeleteOneUserUseCase {
    void execute(Long userId) throws UserNotFoundException;
}
