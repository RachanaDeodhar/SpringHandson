package com.cognizant.ormlearn2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn2.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
	public List<Employee> findAll();
	
	@Query(value="SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
	List<Employee> getAllPermanentEmployees();

	//Similar to avg we can use other aggregate functions as well
	@Query(value="SELECT AVG(e.salary) FROM Employee e")
	double getAverageSalary();
	
	//Native Query - Use it to the minimum as its easier to port without native methods
	@Query(value="SELECT * FROM employee", nativeQuery = true)
	List<Employee> getAllEmployeesNative();
}
