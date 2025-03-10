package com.blautech.ecommerce.users.application.services;

import com.blautech.ecommerce.users.application.ports.in.FindUsersUseCase;
import com.blautech.ecommerce.users.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;

import org.springframework.stereotype.Service;

@Service
public class FindUsersDefaultService implements FindUsersUseCase {
    private final UserPersistencePort userPersistencePort;
    public FindUsersDefaultService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public PaginationResult<User> execute(UserFilters userFilters) {
        return this.userPersistencePort.findUsers(userFilters);
    }
}
