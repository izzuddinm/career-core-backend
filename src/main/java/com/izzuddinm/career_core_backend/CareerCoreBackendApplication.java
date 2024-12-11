package com.izzuddinm.career_core_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@SpringBootApplication
public class CareerCoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerCoreBackendApplication.class, args);
	}

	@Bean
	public Locale locale() {
		return new Locale("en", "US");
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setSupportedLocales(Arrays.asList(new Locale("in", "ID"), new Locale("en", "US")));
		localeResolver.setDefaultLocale(locale());
		return localeResolver;
	}

}
