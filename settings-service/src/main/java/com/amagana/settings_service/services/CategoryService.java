package com.amagana.settings_service.services;


import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

	List<CategoryResponseDTO> getAllCategory();
	CategoryResponseDTO getCategoryById(Long id);
	CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
	CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id);
	void deleteCategory (Long id);
}
