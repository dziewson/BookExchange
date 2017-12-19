package com.p.lodz.pl.bookexchange.main;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootConfiguration
@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = { "com.p.lodz.pl.bookexchange.entities" })
@ComponentScan({ "com.p.lodz.pl.bookexchange.controllers", "com.p.lodz.pl.bookexchange.repositories",
		"com.p.lodz.pl.bookexchange.services", "com.p.lodz.pl.bookexchange.config",
		"com.p.lodz.pl.bookexchange.validators" })

@EnableJpaRepositories("com.p.lodz.pl.bookexchange.repositories")
public class BookExchangeApplication {

	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	
	public static void main(String[] args) {
	
		SpringApplication.run(BookExchangeApplication.class, args);
	}

}
