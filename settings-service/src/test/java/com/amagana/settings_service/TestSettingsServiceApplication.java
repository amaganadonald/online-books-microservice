package com.amagana.settings_service;

import org.springframework.boot.SpringApplication;

public class TestSettingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(SettingsServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
