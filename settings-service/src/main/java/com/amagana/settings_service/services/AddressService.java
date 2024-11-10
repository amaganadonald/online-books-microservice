package com.amagana.settings_service.services;


import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;

import java.util.List;

public interface AddressService {
	
	List<AddressResponseDTO> getAllAddress();
	AddressResponseDTO getAddressById(final Long id);
	AddressResponseDTO addAddress(AddressRequestDTO addressRequestDTO);
	AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO, final Long id);
	Void deleteAddress(final Long id);
}
