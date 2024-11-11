package com.amagana.settings_service.controller;


import com.amagana.settings_service.controllers.CategoryRestController;
import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.services.CategoryService;
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

@WebMvcTest(CategoryRestController.class)
@AutoConfigureWebMvc
class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService categoryService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	CategoryRequestDTO categoryRequestDTO;
	CategoryResponseDTO categoryResponseDTO;
	
	@BeforeEach
	void setUp() {
		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman in book")
				.categoryName("Roman")
				.build();
		categoryResponseDTO = CategoryResponseDTO.builder()
				.id(1L)
				.categoryDescription("Roman in book")
				.categoryName("Roman")
				.build();

	}
	
	@Test
	void shouldReturnAllCategory() throws Exception {
		when(categoryService.getAllCategory()).thenReturn(List.of(categoryResponseDTO));
		this.mockMvc.perform(get("/api/v1/category")).andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].categoryDescription").value("Roman in book"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].categoryName").value("Roman"));
	}
	
	@Test
	void shouldReturnCategoryById() throws Exception {
		when(categoryService.getCategoryById(1L)).thenReturn(categoryResponseDTO);
		this.mockMvc.perform(get("/api/v1/category/"+1)).andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryDescription").value("Roman in book"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryName").value("Roman"));
	}
	
	@Test
	void shouldCreateCategory() throws Exception {
		 when(categoryService.addCategory(categoryRequestDTO)).thenReturn(categoryResponseDTO);
		this.mockMvc.perform(post("/api/v1/category")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                 .andDo(print())
		         .andExpect(status().isCreated())
		         .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryDescription").value("Roman in book"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryName").value("Roman"));

	}
	
	@Test
	void shouldUpdateCategory() throws Exception {
		CategoryResponseDTO categoryResponseDTOUpdate = CategoryResponseDTO.builder()
				.categoryDescription(categoryResponseDTO.categoryDescription())
				.id(categoryResponseDTO.id())
				.categoryName("harlequin")
				.build();
		 when(categoryService.updateCategory(categoryRequestDTO, 1L)).thenReturn(categoryResponseDTOUpdate);
		this.mockMvc.perform(put("/api/v1/category/"+1)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                 .andDo(print())
		         .andExpect(status().isOk())
		         .andExpect(MockMvcResultMatchers.jsonPath("$.results.id").value("1"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryDescription").value("Roman in book"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.results.categoryName").value("harlequin"));

	}
	
	@Test
	void shouldDeleteCategory() throws Exception {
		doNothing().when(categoryService).deleteCategory(1L);
		this.mockMvc.perform(delete("/api/v1/category/"+1))
                 .andDo(print())
		         .andExpect(status().isOk())
	             .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SUCCESS"))
	             .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Category deleted successfully"));

	}
}
