package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	public static void main(String[] args) throws CountryNotFoundException {
		SpringApplication.run(OrmLearnApplication.class, args);
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		//testGetAllCountries();
		//testFindCountryByCode();
		//testAddCountry();
		//testUpdateCountry();
		//testDeleteCountry();
		//testFindByNameContaining();
		testFindByNameStartingWith();
	}
	
	//Retrieve all the records
	private static void testGetAllCountries() 
	{
		 LOGGER.info("Start");
		 List<Country> countries = countryService.getAllCountries();
//		 for(Country c:countries)
//		 {
//			 System.out.println(c.getName());
//		 }
		 LOGGER.debug("countries={}", countries);
		 LOGGER.info("End");
	}

	//Retrieve records by Country Code
	private static void testFindCountryByCode() throws CountryNotFoundException 
	{
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		//System.out.println("Country Code:"+country.getCode()+"Country name:"+country.getName());
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}

	//Insert a new Record
	public static void testAddCountry()
	{
		Country newCountry = new Country();
		newCountry.setCode("AP");
		newCountry.setName("NewCountry");
		countryService.addCountry(newCountry);
	}
	
	//Update record based on code
	public static void testUpdateCountry()
	{	
		countryService.updateCountry("IN", "India");
	}
	
	//Delete record based on code
	public static void testDeleteCountry()
	{	
		countryService.deleteCountry("AP");
	}

	public static void testFindByNameContaining()
	{
		List<Country> countries=countryService.findByNameContaining("ou");
		for(Country c:countries)
		{
			System.out.println(c.getCode()+"  "+c.getName());
		}
	}
	
	public static void testFindByNameStartingWith()
	{
		List<Country> countries=countryService.findByNameStartingWith("Z");
		for(Country c:countries)
		{
			System.out.println(c.getCode()+"  "+c.getName());
		}
	}
}
