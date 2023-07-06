package com.interview.program.logical;

import java.util.ArrayList;
import java.util.List;

public class InterviewProgram02 {
	public static void main(String[] args) {

		String str = "Im charles here";
		String result = "";
		List<String> list = splitMethod(str);
		for (String word : list) {
			if (word.equals("here")) {
				String reverseWord = reverseMethod(word);
				result = replaceWord(str, word, reverseWord);
			}
		}
		System.out.println(result);
	}

	public static List<String> splitMethod(String text) {
		int index = 0;
		int i;
		List<String> list = new ArrayList<String>();
		for (i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ' ') {
				list.add(text.substring(index, i));
				index = i + 1;
			}
		}
		list.add(text.substring(index, i));
		return list;
	}

	public static String reverseMethod(String word) {
		String reverseString = "";
		int size = word.length();
		for (int i = size - 1; i >= 0; i--) {
			reverseString = reverseString + word.charAt(i);
		}
		return reverseString;
	}

	private static String replaceWord(String str, String word, String reverseWord) {
		return str.replaceAll(word, reverseWord);
	}

}
