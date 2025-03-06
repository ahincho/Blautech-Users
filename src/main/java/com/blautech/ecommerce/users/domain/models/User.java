package com.blautech.ecommerce.users.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private LocalDateTime birthday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
