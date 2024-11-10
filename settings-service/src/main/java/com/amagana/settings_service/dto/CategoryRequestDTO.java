package com.amagana.settings_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
public record CategoryRequestDTO (@Size(min = 3, message = "Category name must contains at least 3 words")
								 @NotEmpty(message = "Category name must not be empty") String categoryName, String categoryDescription, LocalDateTime createdAt,
								 String createdBy, String lastUpdatedBy, LocalDateTime lastUpdateDate) {

}
