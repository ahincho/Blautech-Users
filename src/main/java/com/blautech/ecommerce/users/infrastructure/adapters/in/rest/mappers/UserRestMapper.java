package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.mappers;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.Role;
import com.blautech.ecommerce.users.domain.models.Page;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.CreateOneUserRequest;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.PaginationResultResponse;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserQueryRequest;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserRestMapper {
    private UserRestMapper() {}
    public static User createOneRequestToDomain(CreateOneUserRequest createOneUserRequest) {
        return User.builder()
            .id(null)
            .firstname(createOneUserRequest.getFirstname())
            .lastname(createOneUserRequest.getLastname())
            .email(createOneUserRequest.getEmail())
            .address(createOneUserRequest.getAddress())
            .birthday(createOneUserRequest.getBirthday())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
    public static UserResponse domainToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .address(user.getAddress())
            .birthday(user.getBirthday())
            .roles(
                user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet())
            )
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
    public static UserFilters queryRequestToDomain(UserQueryRequest userQueryRequest) {
        return UserFilters.builder()
            .page(
                Page.builder()
                    .number(userQueryRequest.getPage())
                    .size(userQueryRequest.getSize())
                    .build()
            )
            .build();
    }
    public static List<UserResponse> domainListToResponseList(List<User> users) {
        return users.stream()
            .map(UserRestMapper::domainToResponse)
            .toList();
    }
    public static PaginationResultResponse<UserResponse> domainPageToResponsePage(PaginationResult<User> paginationResult) {
        return PaginationResultResponse.<UserResponse>builder()
            .totalItems(paginationResult.getTotalItems())
            .totalPages(paginationResult.getTotalPages())
            .currentPage(paginationResult.getCurrentPage())
            .pageSize(paginationResult.getPageSize())
            .hastNextPage(paginationResult.getHasNextPage())
            .items(domainListToResponseList(paginationResult.getItems()))
            .build();
    }
}
