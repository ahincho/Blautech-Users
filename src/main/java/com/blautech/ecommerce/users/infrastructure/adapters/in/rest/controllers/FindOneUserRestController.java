package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.users.application.ports.in.FindOneUserUseCase;
import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class FindOneUserRestController {
    private final FindOneUserUseCase findOneUserUseCase;
    public FindOneUserRestController(FindOneUserUseCase findOneUserUseCase) {
        this.findOneUserUseCase = findOneUserUseCase;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findOneUser(
        @PathVariable Long userId
    ) throws UserNotFoundException {
        User user = this.findOneUserUseCase.execute(userId);
        return ResponseEntity.ok(UserRestMapper.domainToResponse(user));
    }
}
