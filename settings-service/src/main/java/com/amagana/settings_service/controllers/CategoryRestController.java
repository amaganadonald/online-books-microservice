package com.amagana.settings_service.controllers;

import com.amagana.settings_service.dto.ApiResponse;
import com.amagana.settings_service.dto.CategoryRequestDTO;
import com.amagana.settings_service.dto.CategoryResponseDTO;
import com.amagana.settings_service.enums.StatusResponse;
import com.amagana.settings_service.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private final CategoryService categoryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategory() {
		List<CategoryResponseDTO> category = categoryService.getAllCategory();
		return new ResponseEntity<>(ApiResponse.list(StatusResponse.SUCCESS, category), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
		CategoryResponseDTO category = categoryService.getCategoryById(id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		CategoryResponseDTO category = categoryService.addCategory(categoryRequestDTO);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(@PathVariable Long id,
			@RequestBody CategoryRequestDTO categoryRequestDTO) {
		CategoryResponseDTO category = categoryService.updateCategory(categoryRequestDTO, id);
		return new ResponseEntity<>(ApiResponse.single(StatusResponse.SUCCESS, category), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDTO>> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(ApiResponse.singleMessage(StatusResponse.SUCCESS, "Category deleted successfully"), HttpStatus.OK);
	}
	
	@GetMapping("/fgc/{id}")
	public CategoryResponseDTO categoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
}
