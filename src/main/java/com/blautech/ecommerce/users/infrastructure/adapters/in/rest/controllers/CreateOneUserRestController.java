package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.users.application.ports.in.CreateOneUserUseCase;
import com.blautech.ecommerce.users.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.users.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.users.domain.models.User;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.CreateOneUserRequest;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos.UserResponse;
import com.blautech.ecommerce.users.infrastructure.adapters.in.rest.mappers.UserRestMapper;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class CreateOneUserRestController {
    private final CreateOneUserUseCase createOneUserUseCase;
    public CreateOneUserRestController(CreateOneUserUseCase createOneUserUseCase) {
        this.createOneUserUseCase = createOneUserUseCase;
    }
    @PostMapping
    public ResponseEntity<UserResponse> createOneUser(
        @RequestBody @Valid CreateOneUserRequest createOneUserRequest
    ) throws UserUnderAgeException, UserDuplicateException {
        User user = UserRestMapper.createOneRequestToDomain(createOneUserRequest);
        User savedUser = this.createOneUserUseCase.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(UserRestMapper.domainToResponse(savedUser));
    }
}
