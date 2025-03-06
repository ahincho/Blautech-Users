package com.blautech.ecommerce.users.application.ports.out;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;

public interface UserPersistencePort {
    User createOneUser(User user);
    PaginationResult<User> findUsers(UserFilters userFilters);
    boolean existsOneUserByEmail(String email);
}
