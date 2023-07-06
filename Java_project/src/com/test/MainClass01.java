package com.test;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainClass01 {
	public static void main(String[] args) {

		String str = "helloworld";
		Map<Character, Long> result = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));
		System.out.println(result);
		
		Map<Character, Long> result2 = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result2);
	}
}
