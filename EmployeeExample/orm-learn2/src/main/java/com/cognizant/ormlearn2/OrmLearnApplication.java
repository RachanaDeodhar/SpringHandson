package com.cognizant.ormlearn2;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn2.model.Department;
import com.cognizant.ormlearn2.model.Employee;
import com.cognizant.ormlearn2.service.DepartmentService;
import com.cognizant.ormlearn2.service.EmployeeService;
import com.cognizant.ormlearn2.service.SkillService;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;
	
	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
		//testGetEmployee();
		//testAddEmployee();
		//testUpdateEmployee();
		//testGetDepartment();
		//testGetAllPermanentEmployees();
		//testGetAverageSalary();
		testGetAllEmployeesNative();
	}
	
	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.info("End");
	}

	private static void testAddEmployee()
	{
		Employee employee=new Employee();
		employee.setId(1);
		employee.setName("ABC");
		employee.setSalary(30000);
		employee.setPermanent(true);
		employee.setDateOfBirth(new Date());
		
		Department dept=departmentService.get(1);
		employee.setDepartment(dept);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
	}
	
	private static void testUpdateEmployee()
	{
		Employee employee=employeeService.get(1);
		Department dept=departmentService.get(2);
		employee.setDepartment(dept);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
	}
	
	private static void testGetDepartment()
	{
		Department dept=departmentService.get(2);
		LOGGER.debug("Department:{}", dept);
		Set<Employee> employees=dept.getEmployeeList();
		for(Employee e:employees)
		{
			System.out.println(e.getName());
		}
	}
	
	public static void testGetAllPermanentEmployees() 
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}
	
	public static void testGetAverageSalary() 
	{
		LOGGER.info("Start");
		double averageSalary = employeeService.getAverageSalary();
		LOGGER.debug("AverageSalary:", averageSalary);
		System.out.println("AverageSalary:"+ averageSalary);
		LOGGER.info("End");
	}
	
	public static void testGetAllEmployeesNative() 
	{
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		employees.forEach(e->{System.out.println(e.getId()+" "+e.getName()+" "+e.getDepartment().getName());});
		LOGGER.info("End");
	}
}
