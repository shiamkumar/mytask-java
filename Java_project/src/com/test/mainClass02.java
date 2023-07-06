package com.test;

public class mainClass02 {
	public static void main(String[] args) {

		StrignPermutations("", "bacab");

	}

	public static String StrignPermutations(String prefix, String str) {
		int size = str.length();

		if (size == 0) {
			System.out.println(prefix + " ");
		} else {
			for (int i = 0; i < size; i++) {
				StrignPermutations(prefix + str.charAt(i), str.substring(i + 1, size) + str.substring(0, i));
			}
		}
		return null;
	}

}
