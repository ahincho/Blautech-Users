package com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.repositories;

import com.blautech.ecommerce.users.infrastructure.adapters.out.persistence.jpa.entities.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM user u")
    Page<UserEntity> findUsers(Pageable pageable);
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM user u WHERE u.id IN :userIds")
    List<UserEntity> findUsersWithRoles(@Param("userIds") List<Long> userIds);
    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findById(Long id);
    boolean existsByEmail(String email);
}
