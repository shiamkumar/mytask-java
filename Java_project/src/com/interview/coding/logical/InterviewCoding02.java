package com.interview.coding.logical;

public class InterviewCoding02 {
	public static void main(String[] args) {
		try {
			int v = 1 / 0;
			System.out.println(v);
		} catch (Exception exception) {
			System.out.println("1rd block = Exception");
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("2st block = ArrayIndexOutOfBoundsException");
		} catch (ArithmeticException exception) {
			System.out.println("3nd block = ArithmeticException");
		}
	}
}
