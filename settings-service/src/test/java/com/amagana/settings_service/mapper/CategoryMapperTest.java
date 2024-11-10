package com.amagana.settings_service.mapper;

import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.entity.Category;
import com.amagana.settings_service.mappers.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

	CategoryRequestDTO categoryRequestDTO;
	Category category;

	@BeforeEach
	void setUp() {
		categoryRequestDTO = CategoryRequestDTO.builder()
				.categoryDescription("Roman in book")
				.categoryName("Roman")
				.build();
		category = Category.builder()
				.categoryDescription("Roman in book")
				.categoryName("Roman")
				.build();
	}
	
	@Test
	void testShouldConvertCategoryToCategoryResponseDTO() {
		CategoryResponseDTO categoryResponseDTO = CategoryMapper.INSTANCE.categoryToCategoryResponseDTO(category);
		assertNotNull(categoryResponseDTO);
		assertEquals("Roman", categoryResponseDTO.categoryName());
	}
	
	@Test
	void testShouldCategoryRequestDtoToCategory() {
		Category category = CategoryMapper.INSTANCE.categoryRequestDTOToCategory(categoryRequestDTO);
		assertNotNull(category);
		assertEquals("Roman", category.getCategoryName());
		assertEquals("Roman in book", category.getCategoryDescription());
	}
}
