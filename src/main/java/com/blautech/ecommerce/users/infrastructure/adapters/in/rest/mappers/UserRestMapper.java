package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.mappers;

import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.CreateOneUserRequest;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserResponse;

import java.time.LocalDateTime;

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
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
