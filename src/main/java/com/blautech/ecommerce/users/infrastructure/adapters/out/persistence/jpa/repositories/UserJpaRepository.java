package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.repositories;

import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
