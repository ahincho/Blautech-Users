package com.blautech.ecommerce.users.application.ports.in;

import com.blautech.ecommerce.users.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.users.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.users.domain.models.User;

public interface CreateOneUserUseCase {
    User execute(User user) throws UserUnderAgeException, UserDuplicateException;
}
