package com.htmlman1.capitaleconomy.money.util;

public enum PaymentType { 
	BOTH, CASH, DEBIT;
	
	public static PaymentType getType(String s) {
		return PaymentType.valueOf(s.toUpperCase());
	}
	
	public static boolean isPaymentType(String s) {
		return getType(s) != null;
	}
	
}
