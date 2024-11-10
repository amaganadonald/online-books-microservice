package com.amagana.settings_service.services.impl;

import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.entity.Address;
import com.amagana.settings_service.exceptions.SettingsServiceBusinessException;
import com.amagana.settings_service.mappers.AddressMapper;
import com.amagana.settings_service.repository.AddressRepository;
import com.amagana.settings_service.services.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	public static final AddressMapper addressMapper = AddressMapper.INSTANCE;
	
	@Override
	public List<AddressResponseDTO>  getAllAddress() {
		log.info("AddressService:getAllAddress starting fetch all addresss");
		return addressRepository.findAll()
				.stream()
				.map(addressMapper::addressToAddressResponseDTO)
				.toList();
	}
	
	public Address getAddressByIds(Long id) {
        log.info("AddressService:getAddressById starting to fetch address by id: {}", id);
		 if (id == null || id <= 0) {
			 throw new IllegalArgumentException("Invalid user ID");
		 }

		try {
			Optional<Address> address = addressRepository.findById(id);
            log.info("AddressService:getAddressById fetch address with message::{}", address.toString());
            return address.orElse(null);

        } catch (Exception e) {
            log.error("AddressService:getAddressById fetch address by id failed with message::{}", e.getMessage());
			throw new SettingsServiceBusinessException("fetch address by id failed with message::" + e.getMessage());
		}
		
	}


	@Override
	public AddressResponseDTO getAddressById(Long id) {
		return addressMapper.addressToAddressResponseDTO(getAddressByIds(id));
	}

	@Override
	public AddressResponseDTO addAddress(AddressRequestDTO addressRequestDTO) {
		try {
			Address address = addressMapper.addressDtoToAddress(addressRequestDTO);
			addressRepository.save(address);
			return addressMapper.addressToAddressResponseDTO(address);
		} catch (Exception e) {
            log.error("AddressService:addAddress add address failed with message::{}", e.getMessage());
			throw new SettingsServiceBusinessException("add address failed with message::" + e.getMessage());
		}
	}

	@Override
	public AddressResponseDTO updateAddress(AddressRequestDTO addressRequestDTO, Long id) {
		Address address = getAddressByIds(id);
		try {
			address.setAddressCity(addressRequestDTO.addressCity());
			address.setAddressName(addressRequestDTO.addressName());
			address.setAddressNumber(addressRequestDTO.addressNumber());
			address.setAddressEmail(addressRequestDTO.addressEmail());
			address.setAddressPhone(addressRequestDTO.addressPhone());
			address.setAddressProfessionalPhone(address.getAddressProfessionalPhone());
			addressRepository.save(address);
		} catch (Exception e) {
            log.error("AddressService:updateAddress update address by id failed with message{}", e.getMessage());
			throw new SettingsServiceBusinessException("update address by id failed with message::" + e.getMessage());
		}
		return addressMapper.addressToAddressResponseDTO(address);
	}

	@Override
	public Void deleteAddress (Long id) {
		Address address = getAddressByIds(id);
		try {
			addressRepository.delete(address);
		} catch (Exception e) {
			log.error("Error deleted address with message {}", e.getMessage());
			throw new SettingsServiceBusinessException("Error deleted address with message::" + e.getMessage());
		}
		return null;
	}

}
