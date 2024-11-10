package com.amagana.settings_service.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1194827914545006497L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
