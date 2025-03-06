package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.implementations;

import com.blautech.ecommerce.users.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.users.domain.models.PaginationResult;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.domain.models.UserFilters;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.mappers.UserJpaMapper;
import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.repositories.UserJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserSqlPersistencePort implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    public UserSqlPersistencePort(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    @Override
    @Transactional
    public User createOneUser(User user) {
        UserEntity userEntity = UserJpaMapper.domainToEntity(user);
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);
        return UserJpaMapper.entityToDomain(savedUserEntity);
    }
    @Override
    public PaginationResult<User> findUsers(UserFilters userFilters) {
        Pageable pageable = UserJpaMapper.domainPageToEntityPage(userFilters);
        Page<UserEntity> userEntityPage = userJpaRepository.findAll(pageable);
        return UserJpaMapper.entityPageToDomainPage(userEntityPage);
    }
    @Override
    public Optional<User> findOneUserById(Long id) {
        return this.userJpaRepository.findById(id).map(UserJpaMapper::entityToDomain);
    }
    @Override
    public boolean existsOneUserByEmail(String email) {
        return this.userJpaRepository.existsByEmail(email);
    }
}
