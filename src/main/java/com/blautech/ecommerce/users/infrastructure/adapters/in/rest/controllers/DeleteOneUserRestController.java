package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.controllers;

import com.blautech.ecommerce.users.application.ports.in.DeleteOneUserUseCase;
import com.blautech.ecommerce.users.domain.exceptions.UserNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class DeleteOneUserRestController {
    private final DeleteOneUserUseCase deleteOneUserUseCase;
    public DeleteOneUserRestController(DeleteOneUserUseCase deleteOneUserUseCase) {
        this.deleteOneUserUseCase = deleteOneUserUseCase;
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteOneUser(@PathVariable Long userId) throws UserNotFoundException {
        this.deleteOneUserUseCase.execute(userId);
        return ResponseEntity.noContent().build();
    }
}
