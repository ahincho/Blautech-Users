package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.mappers;

import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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
    public static PaginationResult<User> entityPageToDomainPage(Page<UserEntity> userEntityPage) {
        return PaginationResult.<User>builder()
            .totalItems(userEntityPage.getTotalElements())
            .totalPages(userEntityPage.getTotalElements())
            .currentPage(userEntityPage.getNumber())
            .pageSize(userEntityPage.getSize())
            .hasNextPage(!userEntityPage.hasNext())
            .items(entityListToDomainList(userEntityPage.getContent()))
            .build();
    }
}
