package com.blautech.ecommerce.users.infrastructure.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryRequest {
    @NotNull(message = "Page is required")
    @PositiveOrZero(message = "Page must be 0 or positive")
    private Integer page = 0;
    @NotNull(message = "Size is required")
    @Positive(message = "Size must be positive")
    private Integer size = 10;
}
