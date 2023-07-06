package com.test;

import java.util.Arrays;
import java.util.List;

public class MainClass03 {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(4, 3, 6, 1, 7, 5, 2, 10, 11, 100);
		int target = 7;

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i) + list.get(j) == target) {
					System.out.println("pairs are: " + list.get(i) + " , " + list.get(j));
				}
			}
		}
		
	}
}
