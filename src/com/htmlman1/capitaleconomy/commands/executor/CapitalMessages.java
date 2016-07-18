package com.htmlman1.capitaleconomy.commands.executor;

import java.text.DecimalFormat;

public class CapitalMessages {

	private static final DecimalFormat CASH_FORMAT = new DecimalFormat("#.##");
	public static final String BE_PLAYER = "§cYou must be a player to do this.";
	public static final String CANNOT_AFFORD = "§cYou can't afford to do this!";
	public static final String DOES_NOT_EXIST = "§cThat player does not exist.";
	public static final String NOT_ONLINE = "§cThat player is not online.";
	public static final String NO_PERMS = "§cYou don't have permission to do this.";
	public static final String USE_NUMBER = "§cPlease specify a numerical amount!";
	
	public static String toCashFormat(double d) {
		return CapitalMessages.CASH_FORMAT.format(d);
	}
	
}
