package com.test.functionalinterfaces.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.test.functionalinterfaces.consumer.Employee;

public class WithSupplier {
	public static void main(String[] args) {
		Supplier<List<Employee>> empSupplier = () -> getAllUsers();
	    System.out.println("Getting the employees: " + empSupplier.get());

	}

	private static List<Employee> getAllUsers() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(123, "Jack", 20000));
		employees.add(new Employee(432, "Peter", 50000));
		employees.add(new Employee(512, "Dennis", 30000));
		employees.add(new Employee(336, "Kelly", 10000));
		return employees;
	}
}
