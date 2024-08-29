package com.learn.aws.service;

import com.learn.aws.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl {
	static List<Employee> employees = new java.util.ArrayList(Arrays.asList(
			new Employee(0L, "Manoj0", "mpatidar0@test.com"), new Employee(1L, "Manoj1", "mpatidar1@test.com"),
			new Employee(2L, "Manoj2", "mpatidar2@test.com"), new Employee(3L, "Manoj3", "mpatidar3@test.com"),
			new Employee(4L, "Manoj4", "mpatidar4@test.com"), new Employee(5L, "Manoj5", "mpatidar5@test.com")));

	public List<Employee> getAllEmployee() {
		return employees;
	}

	public void save(Employee employee) {
		employees.add(employee);

	}

	public Employee getById(Integer id) {
		Employee employee;
		try {
			employee = employees.get(id);
		} catch (Exception e) {
			throw new RuntimeException("Employee not found for id : " + id);
		}
		return employee;
	}

	public void deleteViaId(int id) {
		System.out.println("Deleting Employee with id : " + id);
		Employee employee = employees.get(id);
		employees.remove(employee);
	}
}
