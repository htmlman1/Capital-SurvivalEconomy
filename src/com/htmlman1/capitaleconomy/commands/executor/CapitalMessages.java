package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;

public class CapitalMessages {

	private static final DecimalFormat CASH_FORMAT = new DecimalFormat("#.##");
	public static final String BE_PLAYER = ChatColor.RED+"You must be a player to do this.";
	public static final String CANNOT_AFFORD = ChatColor.RED+"You can't afford to do this!";
	public static final String DOES_NOT_EXIST = ChatColor.RED+"That player does not exist.";
	public static final String NOT_ONLINE = ChatColor.RED+"That player is not online.";
	public static final String NO_PERMS = ChatColor.RED+"You don't have permission to do this.";
	public static final String USE_NUMBER = ChatColor.RED+"Please specify a numerical amount!";
	
	public static String toCashFormat(double d) {
		return CapitalMessages.CASH_FORMAT.format(d);
	}
	
}
