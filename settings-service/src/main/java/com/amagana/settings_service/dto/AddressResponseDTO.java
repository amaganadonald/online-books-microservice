package com.amagana.settings_service.dto;

import lombok.*;

import java.time.LocalDateTime;


@Builder
public record AddressResponseDTO (

	 Long id,
	 String addressName,
	 String addressCity,
	 int addressNumber,
	 String addressEmail,
	 String addressPhone,
	 String addressProfessionalPhone,
	 LocalDateTime createdAt,
	 String createdBy,
	 String lastUpdatedBy,
	 LocalDateTime lastUpdateDate) {

}
