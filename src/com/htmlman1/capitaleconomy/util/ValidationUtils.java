package com.htmlman1.capitaleconomy.util;

public class ValidationUtils {

	public static boolean isDouble(String test) {
		try {
			Double.parseDouble(test);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
