package com.amagana.settings_service.service;


import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.entity.Address;
import com.amagana.settings_service.mappers.AddressMapper;
import com.amagana.settings_service.repository.AddressRepository;
import com.amagana.settings_service.services.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	AddressRepository addressRepository;
	
	@InjectMocks
	AddressServiceImpl addressServiceImpl;
	
	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	Address address;

	
	@BeforeEach
	void setUp() {

		addressRequestDTO = AddressRequestDTO.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();
		address = Address.builder()
				.addressCity("Mulhenbach")
				.addressEmail("donald9do@gmail.com")
				.addressName("Huberty")
				.addressNumber(37)
				.addressPhone("6215463737")
				.addressProfessionalPhone("6945664545")
				.build();

		//when(AddressMapper.INSTANCE.addressToAddressResponseDTO( address )).thenReturn(addressResponseDTO);
		//when(AddressMapper.INSTANCE.addressDtoToAddress(addressRequestDTO)).thenReturn(address);
	}

	
	@Test
	void shouldReturnAllAddress() {
		when(addressRepository.findAll()).thenReturn(List.of(address));
		List<AddressResponseDTO> cat = addressServiceImpl.getAllAddress();
		assertEquals(1, cat.size());
		assertEquals(1, cat.size());
		assertEquals("Mulhenbach", cat.getFirst().addressCity());
	}
	
	@Test
	void shouldAddAddressThenReturnAddressResponseDTO() {
		when(addressRepository.save(any(Address.class))).thenReturn(address);
		AddressResponseDTO cat = addressServiceImpl.addAddress(addressRequestDTO);
		assertEquals(37, cat.addressNumber());
		assertEquals("Mulhenbach", cat.addressCity());
	}
	
	@Test
	void shouldUpdateAddressThenReturnAddressResponseDTO() {
		when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
		when(addressRepository.save(address)).thenReturn(address);
		AddressResponseDTO cat = addressServiceImpl.updateAddress(addressRequestDTO, 1L);
		assertEquals(37, cat.addressNumber());
		assertEquals("Mulhenbach", cat.addressCity());
	}
	
	@Test
	void shouldDeleteAddress() {
		addressRepository.delete(address);
		Mockito.verify(addressRepository).delete(address);
	}
}
