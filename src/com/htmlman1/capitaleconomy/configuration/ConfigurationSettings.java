package com.htmlman1.capitaleconomy.configuration;

import org.bukkit.configuration.Configuration;

import com.htmlman1.capitaleconomy.money.util.PaymentType;

public class ConfigurationSettings {

	public static PaymentType defaultPayment = PaymentType.DEBIT;
	public static boolean disableTrades = false;
	public static double bankTax = 0d;
	public static double globalTax = 8d;
	public static double lotteryDist = 75d;
	public static double marketDist = 25d;
	public static double ticketPrice = 25d;
	
	public static void init(Configuration c) {
		defaultPayment = PaymentType.getType(c.getString("default-payment"));
		disableTrades = c.getBoolean("disable-trades");
		bankTax = c.getDouble("taxes.contexts.bank");
		globalTax = c.getDouble("taxes.contexts.global");
		lotteryDist = c.getDouble("taxes.distribution.server-lottery");
		marketDist = c.getDouble("taxes.distribution.server-market");
		ticketPrice = c.getDouble("ticket-price");
	}
	
}
