package com.cognizant.ormlearn2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn2.model.Employee;
import com.cognizant.ormlearn2.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public Employee get(int id) 
	{
		return employeeRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Employee employee) 
	{
		employeeRepository.save(employee);
	}

	public List<Employee> getAllPermanentEmployees() {
		return employeeRepository.getAllPermanentEmployees();
	}

	public double getAverageSalary() {
		return employeeRepository.getAverageSalary();
	}
	
	public List<Employee> getAllEmployeesNative(){
		return employeeRepository.getAllEmployeesNative();
	}
}
