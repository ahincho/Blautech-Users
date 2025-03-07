package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.mappers;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.Role;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.RoleEntity;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            .roles(
                userEntity.getRoles() == null || userEntity.getRoles().isEmpty()
                    ? Collections.emptySet()
                    : userEntity.getRoles().stream()
                        .map(UserJpaMapper::entityRoleToDomainRole)
                        .collect(Collectors.toSet())
            )
            .createdAt(userEntity.getCreatedAt())
            .updatedAt(userEntity.getUpdatedAt())
            .build();
    }
    public static Role entityRoleToDomainRole(RoleEntity roleEntity) {
        return Role.builder()
            .id(roleEntity.getId())
            .name(roleEntity.getName())
            .build();
    }
    public static Pageable domainPageToEntityPage(UserFilters userFilters) {
        return PageRequest.of(
            userFilters.getPage().getNumber(),
            userFilters.getPage().getSize(),
            Sort.by(Sort.Direction.ASC, "id")
        );
    }
    public static List<User> entityListToDomainList(List<UserEntity> userEntities) {
        return userEntities.stream()
            .map(UserJpaMapper::entityToDomain)
            .toList();
    }
    public static PaginationResult<User> entityPageToDomainPage(Page<UserEntity> userEntityPage, List<UserEntity> userEntities) {
        return PaginationResult.<User>builder()
            .totalItems(userEntityPage.getTotalElements())
            .totalPages(userEntityPage.getTotalPages())
            .currentPage(userEntityPage.getNumber())
            .pageSize(userEntityPage.getSize())
            .hasNextPage(userEntityPage.hasNext())
            .items(entityListToDomainList(userEntities))
            .build();
    }
}
