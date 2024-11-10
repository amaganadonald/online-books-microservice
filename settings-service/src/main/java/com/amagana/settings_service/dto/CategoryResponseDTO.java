package com.amagana.settings_service.dto;

import lombok.*;

import java.time.LocalDateTime;


@Builder
public record CategoryResponseDTO(Long id, String categoryName, String categoryDescription, LocalDateTime createdAt,
								  String createdBy, String lastUpdatedBy, LocalDateTime lastUpdateDate) {

}
