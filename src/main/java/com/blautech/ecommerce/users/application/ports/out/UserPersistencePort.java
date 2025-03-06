package com.blautech.ecommerce.users.application.ports.out;

import com.blautech.ecommerce.users.domain.models.User;

public interface UserPersistencePort {
    User createOneUser(User user);
    boolean existsOneUserByEmail(String email);
}
