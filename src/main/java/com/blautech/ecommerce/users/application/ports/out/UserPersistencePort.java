package com.blautech.ecommerce.users.application.ports.out;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;

import java.util.Optional;

public interface UserPersistencePort {
    User createOneUser(User user);
    PaginationResult<User> findUsers(UserFilters userFilters);
    Optional<User> findOneUserById(Long id);
    boolean existsOneUserById(Long id);
    boolean existsOneUserByEmail(String email);
    void deleteOneUserById(Long id);
}
