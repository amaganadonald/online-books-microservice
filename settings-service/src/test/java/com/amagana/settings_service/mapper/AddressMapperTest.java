package com.amagana.settings_service.mapper;

import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.entity.Address;
import com.amagana.settings_service.mappers.AddressMapper;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressMapperTest {

	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	Address address;

	
	@BeforeEach
	void setUp() {
		addressRequestDTO =	AddressRequestDTO.builder()
				.addressName("Lux ville")
				.addressCity("Hamilus")
				.addressNumber(37)
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.id(1L)
				.build();
		address = Address.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.build();
	}
	
	@Test
	void shouldMapAddressToAddressResponseDTO() {
		AddressResponseDTO response = AddressMapper.INSTANCE.addressToAddressResponseDTO(address);
		assertNotNull(response);
		assertEquals("Mulhenbachh", response.addressCity());
	}
	
	@Test
	void shouldToMapAddressRequestToAddress() {
		Address response = AddressMapper.INSTANCE.addressDtoToAddress(addressRequestDTO);
		assertNotNull(response);
		assertEquals("Lux ville", response.getAddressName());
		assertEquals("Hamilus", response.getAddressCity());
	}

	@Test
	void shouldNotMapAddressRequestToAddress() {
		Assertions.assertThat(AddressMapper.INSTANCE.addressDtoToAddress(null)).isNull();

	}
	
}
