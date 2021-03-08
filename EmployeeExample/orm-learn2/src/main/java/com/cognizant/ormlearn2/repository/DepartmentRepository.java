package com.cognizant.ormlearn2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn2.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
	public List<Department> findAll();
}
