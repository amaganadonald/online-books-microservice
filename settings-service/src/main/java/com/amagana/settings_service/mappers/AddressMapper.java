package com.amagana.settings_service.mappers;


import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
	
	public AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	public Address addressDtoToAddress(AddressRequestDTO addressRequestDTO) ;

	
	public AddressResponseDTO addressToAddressResponseDTO(Address address);

}
