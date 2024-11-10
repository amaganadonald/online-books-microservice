package com.amagana.settings_service.service;

import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.entity.Category;
import com.amagana.settings_service.mappers.CategoryMapper;
import com.amagana.settings_service.repository.CategoryRepository;
import com.amagana.settings_service.services.impl.CategoryServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	
	CategoryRequestDTO categoryRequestDTO;
	CategoryResponseDTO categoryResponseDTO;
	Category category;
	

	
	@BeforeEach
	void setUp() {

		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		categoryResponseDTO = CategoryResponseDTO.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		category = Category.builder()
				.categoryDescription("Roman en livre")
				.categoryName("Roman")
				.build();
		when(CategoryMapper.INSTANCE.categoryRequestDTOToCategory(categoryRequestDTO)).thenReturn(category);
		when(CategoryMapper.INSTANCE.categoryToCategoryResponseDTO(category)).thenReturn(categoryResponseDTO);
	}

	
	@Test
	void shouldReturnAllCategory() {
		when(categoryRepository.findAll()).thenReturn(List.of(category));
		List<CategoryResponseDTO> cat = categoryServiceImpl.getAllCategory();
		assertEquals(1, cat.size());
		assertEquals("Roman", cat.getFirst().categoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldAddCategoryThenReturnCategoryResponseDTO() {
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryResponseDTO cat = categoryServiceImpl.addCategory(categoryRequestDTO);
		assertEquals("Roman", cat.categoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldUpdateCategoryThenReturnCategoryResponseDTO() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryResponseDTO cat = categoryServiceImpl.updateCategory(categoryRequestDTO, 1L);
		assertEquals("Roman", cat.categoryName());
		assertNotNull(cat);
	}
	
	@Test
	void shouldDeleteCategory() {
		categoryRepository.delete(category);
		Mockito.verify(categoryRepository).delete(category);
	}
}
