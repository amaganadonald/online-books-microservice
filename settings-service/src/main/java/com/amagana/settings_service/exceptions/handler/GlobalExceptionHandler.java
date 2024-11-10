package com.amagana.settings_service.exceptions.handler;


import com.amagana.settings_service.dto.ApiResponse;
import com.amagana.settings_service.dto.ErrorsDTO;
import com.amagana.settings_service.enums.StatusResponse;
import com.amagana.settings_service.exceptions.EntityNotFoundException;
import com.amagana.settings_service.exceptions.SettingsServiceBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<ErrorsDTO>> entitiesValidationFailed(MethodArgumentNotValidException exception) {
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		log.error("Error message::"+errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.errors(StatusResponse.FAILED,
				"Error occur when validation field", errors));
	}
	

	@ExceptionHandler(SettingsServiceBusinessException.class)
	public ResponseEntity<ApiResponse<String>> onlineBookBusinessException(SettingsServiceBusinessException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.singleMessage(StatusResponse.FAILED, 
				exception.getMessage()));
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> entityNotFoundException(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.singleMessage(StatusResponse.FAILED, 
				exception.getMessage()));
	}

}
