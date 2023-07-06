package com.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeDemo {
	public static void main(String[] args) {
		
		Employee e1 = new Employee(10001, "Arun", 23);
		Employee e2 = new Employee(10003, "Arun", 26);
		Employee e3 = new Employee(10004, "Arun", 32);
		Employee e4 = new Employee(10002, "Arun", 18);
		
		List<Employee> employees = Arrays.asList(e1, e2, e3, e4);
		
		Map<Integer, List<Employee>> employeeByAge = 
				employees
				.stream()
				.collect(Collectors.groupingBy(
					Employee::getAge, Collectors.toList()));
			
		employeeByAge.forEach(null);
		
	}
}

