package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.users.application.ports.in.FindUsersUseCase;
import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserQueryRequest;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class FindUsersRestController {
    private final FindUsersUseCase findUsersUseCase;
    public FindUsersRestController(FindUsersUseCase findUsersUseCase) {
        this.findUsersUseCase = findUsersUseCase;
    }
    @GetMapping
    public ResponseEntity<PaginationResultResponse<UserResponse>> findUsers(
        @ModelAttribute @Valid UserQueryRequest userQueryRequest
    ) {
        UserFilters userFilters = UserRestMapper.queryRequestToDomain(userQueryRequest);
        PaginationResult<User> userPaginationResult = this.findUsersUseCase.execute(userFilters);
        if (userPaginationResult.getItems().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(UserRestMapper.domainPageToResponsePage(userPaginationResult));
    }
}
