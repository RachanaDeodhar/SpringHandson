package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;

	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}

	private static void getAllCountriesTest() {

		LOGGER.info("Start");

		Country country = null;
		try {
			country = countryService.findCountryByCode("IN");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	private static void testAddCountry() {
		LOGGER.info("Start");

		Country country = new Country();
		country.setCode("ZE");
		country.setName("Zee");

		countryService.addCountry(country);
		try {
			countryService.findCountryByCode("ZE");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	public static void updateCountryTest() {
		LOGGER.info("Start");

		try {
			countryService.updateCountry("ZE", "Zero");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.info("End");

	}
	
	public static void deleteCountryTest() {
		LOGGER.info("Start");
		countryService.deleteCountry("ZE");
		LOGGER.info("End");

	}
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);

		LOGGER.info("Inside main");
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		updateCountryTest();
		deleteCountryTest();
	}

}
