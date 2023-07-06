package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainClass04 {
	public static void main(String[] args) {
		
//		List<Integer> list = Arrays.asList(1,2,3,1,2,3,4,5);
//		
//		Map<Integer, Long> result = list
//				.stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		System.out.println(result);
		
		String s = "my name is charles";
		String[] strArr = s.split("[\\s.]");
		List<String> list = new ArrayList<>();
		for(String word : strArr) {
			list.add(word);	
			wordReverse(list);
		}
		
	}
	
	public static void wordReverse(List<String> list) {
		List<String> result = list
		.stream()
		.sorted(Comparator.comparing(null))
		.collect(Collectors.toList());
		System.out.println(result);
	}
	
}
