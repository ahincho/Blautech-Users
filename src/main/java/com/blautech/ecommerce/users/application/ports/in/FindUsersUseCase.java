package com.blautech.ecommerce.users.application.ports.in;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;

public interface FindUsersUseCase {
    PaginationResult<User> execute(UserFilters userFilters);
}
