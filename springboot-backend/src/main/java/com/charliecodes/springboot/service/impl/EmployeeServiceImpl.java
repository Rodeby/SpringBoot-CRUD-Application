package com.charliecodes.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.charliecodes.springboot.exception.ResourceNotFoundException;
import com.charliecodes.springboot.model.Employee;
import com.charliecodes.springboot.repository.EmployeeRepository;
import com.charliecodes.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {

		// Optional<Employee> employee = employeeRepository.findById(id);
		// if(employee.isPresent()) {
		// return employee.get();
		// }else {
		// throw new ResourceNotFoundException("Employee", "Id",id);
		// }

		// Lambda expression eqv. of above
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// check if employee with id exists in DB
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save employee to DB to update parameters
		employeeRepository.save(existingEmployee);
		return existingEmployee;

	}

	@Override
	public void deleteEmployee(long id) {

		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);

	}

}
