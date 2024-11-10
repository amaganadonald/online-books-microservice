package com.amagana.settings_service.mappers;


import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
	
	public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	public Category categoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO);
	
	public CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
}
