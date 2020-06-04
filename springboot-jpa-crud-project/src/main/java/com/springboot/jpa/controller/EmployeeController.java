package com.springboot.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.exception.ResourceNotFoundException;
import com.springboot.jpa.model.Employee;
import com.springboot.jpa.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id")Long empId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found for this id ::"+empId));

		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id")Long empId,@Valid @RequestBody Employee employee) throws ResourceNotFoundException{
		Employee emp = employeeRepository.findById(empId)
							.orElseThrow(()->new ResourceNotFoundException("Employee not found for this id ::"+empId));
		emp.setEmailId(employee.getEmailId());
		emp.setFirstname(employee.getFirstname());
		emp.setLastname(employee.getLastname());
		final Employee updateEmp = employeeRepository.save(emp);
		return ResponseEntity.ok(updateEmp);
	}
	
	@RequestMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id")Long empId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(empId)
							.orElseThrow(()->new ResourceNotFoundException("Employee not found for this id ::"+empId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
}
