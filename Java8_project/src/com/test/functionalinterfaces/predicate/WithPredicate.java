package com.test.functionalinterfaces.predicate;

import java.util.Arrays;
import java.util.List;

import com.test.functionalinterfaces.consumer.Employee;

public class WithPredicate { //Predicate implemented in filter()
	public static void main(String[] args) {
		Employee emp1 = new Employee(123, "Jack", 20000);
		Employee emp2 = new Employee(432, "Peter", 50000);
		Employee emp3 = new Employee(512, "Dennis", 30000);
		Employee emp4 = new Employee(336, "Kelly", 10000);
		List<Employee> employees = Arrays.asList(emp1, emp2, emp3, emp4);

		employees.stream()
			.filter(employee -> (employee.getSalary() > 10000))
			.filter(employee -> (employee.getSalary() < 40000))
			.forEach((employee) -> System.out.println("Highest Salary Employee: " + employee.getName()));

	}
}