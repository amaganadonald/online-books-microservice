package com.amagana.settings_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Builder
public record AddressRequestDTO (

		@Size(min = 3, message = "Address name must contains at least 3 words")
		@NotEmpty(message = "Address name must not be empty") String addressName,
		String addressCity,
		@NotNull(message = "Address number must no be not null")
		int addressNumber,
		String addressEmail,
		String addressPhone,
		String addressProfessionalPhone,
		LocalDateTime createdAt,
		String createdBy,
		String lastUpdatedBy,
		LocalDateTime lastUpdateDate) {

}

