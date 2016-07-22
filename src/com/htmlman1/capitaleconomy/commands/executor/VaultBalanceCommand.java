package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.user.ServerCapitalUser;

public class VaultBalanceCommand {

	public static void execute(CommandSender sender) {
		if(sender instanceof Player) {
			CapitalUser user = CapitalUserFactory.getUser((Player) sender);
			if(user.hasVault()) {
				if(user.hasPermission(CapitalPermission.CHECK_THEIR_VAULT) || user.hasPermission(CapitalPermission.CHECK_ALL_VAULT)) {
					String typeAmounts = user.getCapitalVault().getTypeAmounts();
					user.sendMessage(ChatColor.GRAY+"You have " +ChatColor.GOLD+"$" + CapitalMessages.toCashFormat(user.getCapitalVault().getValue()) + ChatColor.GRAY + " worth of items in your vault" + ((typeAmounts == "") ? "." : ", consisting of " + typeAmounts));
				} else {
					throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
				}
			} else {
				throw new IllegalArgumentException(ChatColor.RED+"You don't have a vault registered.");
			}
		} else if(sender instanceof ConsoleCommandSender) {
			ServerCapitalUser user = CapitalUserFactory.getServerUser();
			String typeAmounts = user.getVault().getTypeAmounts();
			user.sendMessage(ChatColor.GRAY+"You have " + ChatColor.GOLD+"$" + CapitalMessages.toCashFormat(user.getVault().getValue()) + ChatColor.GRAY + " worth of items in your vault" + ((typeAmounts == "") ? "." : ", consisting of " + typeAmounts));
		}
	}
	
}
