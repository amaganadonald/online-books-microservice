package com.amagana.settings_service.services.impl;

import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.entity.Category;
import com.amagana.settings_service.exceptions.SettingsServiceBusinessException;
import com.amagana.settings_service.mappers.CategoryMapper;
import com.amagana.settings_service.repository.CategoryRepository;
import com.amagana.settings_service.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	public static final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Override
	public List<CategoryResponseDTO> getAllCategory() {
		log.info("CategoryService:getAllCategory starting fetch all category");
		return categoryRepository.findAll()
				.stream()
				.map(categoryMapper::categoryToCategoryResponseDTO)
				.toList();
	}
	
	public Category getCategoryByIds(Long id) {
        log.info("CategoryService:getCategoryByIds starting to fetch category by id::{}", id);
		 if (id == null || id <= 0)
			 throw new IllegalArgumentException("Invalid user ID");
		try {
			Optional<Category> category = categoryRepository.findById(id);
            log.info("CategoryService:getCategoryByIds fetch category with message::{}", category.toString());
			return category.orElse(null);
			
		} catch (Exception e) {
            log.error("CategoryService:getCategoryByIds fetch category by id failed with message::{}", e.getMessage());
			throw new SettingsServiceBusinessException("fetch category by id failed with message::" + e.getMessage());
		}
		
	}

	@Override
	public CategoryResponseDTO getCategoryById(Long id) {
		return categoryMapper.categoryToCategoryResponseDTO(getCategoryByIds(id));
	}

	@Override
	public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
		try {
			Category category = categoryMapper.categoryRequestDTOToCategory(categoryRequestDTO);
			categoryRepository.save(category);
			return categoryMapper.categoryToCategoryResponseDTO(category);
		} catch (Exception e) {
            log.error("CategoryService:addCategory add category failed with message::{}", e.getMessage());
			throw new SettingsServiceBusinessException("add category failed with message::" + e.getMessage());
		}
	}

	@Override
	public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long id) {
		Category category = getCategoryByIds(id);
		try {
			if (category != null) {
				category.setCategoryName(categoryRequestDTO.categoryName());
				category.setCategoryDescription(categoryRequestDTO.categoryDescription());
				categoryRepository.save(category);
				return categoryMapper.categoryToCategoryResponseDTO(category);
			}
		} catch (Exception e) {
            log.error("CategoryService:updateCategory update category by id failed with message::{}", e.getMessage());
			throw new SettingsServiceBusinessException("update category by id failed with message" + e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = getCategoryByIds(id);
		try {
			categoryRepository.delete(category);
		} catch (Exception e) {
			log.error("Error deleted category with message {}", e.getMessage());
			throw new SettingsServiceBusinessException("Error deleted category with message" + e.getMessage());
		}
	}

	
}
