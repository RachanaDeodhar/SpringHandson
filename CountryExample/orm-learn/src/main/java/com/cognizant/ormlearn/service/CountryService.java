package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService 
{
	@Autowired
	public CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries()
	{
		List<Country> countries = countryRepository.findAll();
		return countries;
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		if (!result.isPresent())
		{
			throw new CountryNotFoundException();
		}
		Country country = result.get();
		return country;
	}
	
	@Transactional
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code,String newName)
	{
		Optional<Country> c=countryRepository.findById(code);
		Country country = c.get();
		country.setName(newName);
		countryRepository.save(country);
	}
	
	@Transactional
	public void deleteCountry(String code)
	{
		countryRepository.deleteById(code);
	}
	
	@Transactional
	public List<Country> findByNameContaining(String word)
	{
		List<Country> countries =countryRepository.findByNameContainingOrderByNameAsc(word);
		return countries;
	}
	
	@Transactional
	public List<Country> findByNameStartingWith(String letter)
	{
		List<Country> countries =countryRepository.findByNameStartingWith(letter);
		return countries;
	}
}
