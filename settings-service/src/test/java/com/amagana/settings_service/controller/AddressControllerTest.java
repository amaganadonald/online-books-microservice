package com.amagana.settings_service.controller;

import com.amagana.settings_service.controllers.AddressRestController;
import com.amagana.settings_service.dto.AddressRequestDTO;
import com.amagana.settings_service.dto.AddressResponseDTO;
import com.amagana.settings_service.services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AddressRestController.class)
@AutoConfigureWebMvc
class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AddressService addressService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	
	@BeforeEach
	void setUp() {
		addressRequestDTO =	AddressRequestDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.id(1L)
				.build();
	}
	
	@Test
	void shouldReturnAllAddress() throws Exception {
		when(addressService.getAllAddress()).thenReturn(List.of(addressResponseDTO));
		this.mockMvc.perform(get("/api/v1/address")).andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].addressName").value("Jean Huberty"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].addressCity").value("Mulhenbachh"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].addressNumber").value("37"));
	}
	
	@Test
	void shouldReturnAddressById() throws Exception {
		when(addressService.getAddressById(1L)).thenReturn(addressResponseDTO);
		this.mockMvc.perform(get("/api/v1/address/"+1)).andDo(print()).andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressName").value("Jean Huberty"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressCity").value("Mulhenbachh"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressNumber").value("37"));
	}
	
	@Test
	void shouldCreateAddress() throws Exception {
		 when(addressService.addAddress(addressRequestDTO)).thenReturn(addressResponseDTO);
		this.mockMvc.perform(post("/api/v1/address")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(addressRequestDTO)))
                 .andDo(print())
		         .andExpect(status().isCreated())
		         .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressName").value("Jean Huberty"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressCity").value("Mulhenbachh"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressNumber").value("37"));

	}
	
	@Test
	void shouldUpdateAddress() throws Exception {
		AddressResponseDTO addressResponseDTOUpdate = AddressResponseDTO.builder()
				.addressCity(addressResponseDTO.addressCity())
				.addressEmail(addressResponseDTO.addressEmail())
				.addressName(addressResponseDTO.addressName())
				.addressNumber(40)
				.id(addressResponseDTO.id())
				.build();
		 when(addressService.updateAddress(addressRequestDTO, 1L)).thenReturn(addressResponseDTOUpdate);
		this.mockMvc.perform(put("/api/v1/address/"+1)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(addressRequestDTO)))
                 .andDo(print())
		         .andExpect(status().isAccepted())
		         .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressName").value("Jean Huberty"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressCity").value("Mulhenbachh"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.addressNumber").value("40"));

	}
	
	@Test
	void shouldDeleteAddress() throws Exception {
		doNothing().when(addressService).deleteAddress(1L);
		this.mockMvc.perform(delete("/api/v1/address/"+1))
                 .andDo(print())
		         .andExpect(status().isOk())
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Address deleted successfully"));

	}

}
