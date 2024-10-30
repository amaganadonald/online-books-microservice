package com.amagana.settings_service.exceptions;

import java.io.Serial;

public class SettingsServiceBusinessException extends RuntimeException {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 3948530750876073854L;

	public SettingsServiceBusinessException(String message) {
		super(message);
	}

}
