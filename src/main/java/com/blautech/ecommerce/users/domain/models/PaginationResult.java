package com.blautech.ecommerce.users.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResult<T> {
    private Long totalItems;
    private Long totalPages;
    private Integer currentPage;
    private Integer pageSize;
    private Boolean hasNextPage;
    private List<T> items;
}
