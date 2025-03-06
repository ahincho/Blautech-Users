package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.mappers;

import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

public class UserJpaMapper {
    private UserJpaMapper() {}
    public static UserEntity domainToEntity(User user) {
        return UserEntity.builder()
            .id(user.getId() == null ? null : user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .address(user.getAddress())
            .birthday(user.getBirthday())
            .createdAt(user.getCreatedAt() == null ? null : user.getCreatedAt())
            .updatedAt(user.getUpdatedAt() == null ? null : user.getUpdatedAt())
            .build();
    }
    public static User entityToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .firstname(userEntity.getFirstname())
            .lastname(userEntity.getLastname())
            .email(userEntity.getEmail())
            .address(userEntity.getAddress())
            .birthday(userEntity.getBirthday())
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
    }
}
