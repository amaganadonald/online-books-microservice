package com.amagana.settings_service.controllers;

import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.dto.ApiResponse;
import com.amagana.settings_service.enums.StatusResponse;
import com.amagana.settings_service.services.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
@CrossOrigin(origins = "*")
public class AddressRestController {

	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<AddressResponseDTO>>> getAllAddress() {
		List<AddressResponseDTO> addressResponseDTO = addressService.getAllAddress();
		return new ResponseEntity<>(ApiResponse.list(StatusResponse.SUCCESS, addressResponseDTO), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponseDTO>> getAddressById(@PathVariable Long id) {
		AddressResponseDTO addressResponseDTO = addressService.getAddressById(id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, addressResponseDTO), HttpStatus.FOUND);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<AddressResponseDTO>> addAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO) {
		AddressResponseDTO addressResponseDTO = addressService.addAddress(addressRequestDTO);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, addressResponseDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponseDTO>> updateAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO ,@PathVariable Long id) {
		AddressResponseDTO addressResponseDTO = addressService.updateAddress(addressRequestDTO, id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, addressResponseDTO), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponseDTO>> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return new ResponseEntity<>(ApiResponse.singleMessage(StatusResponse.SUCCESS, "Address deleted successfully"), HttpStatus.OK);
	}

	@GetMapping("/ofc/{id}")
	public AddressResponseDTO findAddressById(@PathVariable Long id) {
		return addressService.getAddressById(id);
	}
}
