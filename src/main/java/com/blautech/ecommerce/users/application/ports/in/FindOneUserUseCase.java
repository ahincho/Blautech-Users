package com.blautech.ecommerce.users.application.ports.in;

import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.users.domain.models.User;

public interface FindOneUserUseCase {
    User execute(Long userId) throws UserNotFoundException;
}
