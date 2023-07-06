package com.test.functionalinterfaces.consumer;

import java.util.Arrays;
import java.util.List;

public class WithOutConsumer {
	public static void main(String[] args) {
		Employee emp1 = new Employee(123, "Jack", 20000);
		Employee emp2 = new Employee(432, "Peter", 50000);
		Employee emp3 = new Employee(512, "Dennis", 30000);
		Employee emp4 = new Employee(336, "Kelly", 30000);
	    List<Employee> employees = Arrays.asList(emp1, emp2, emp3, emp4);
	    
        for(Employee employee : employees){
            System.out.println("Employee Name: "+ employee.getName().toUpperCase());
        }
	}
}
