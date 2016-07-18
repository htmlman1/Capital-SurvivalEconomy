package com.htmlman1.capitaleconomy.commands.executor;

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
					user.sendMessage("§8You have §6$" + user.getCapitalVault().getValue() + " §8worth of items in your vault, consisting of " + user.getCapitalVault().getTypeAmounts());
				} else {
					throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
				}
			} else {
				throw new IllegalArgumentException("§cYou don't have a vault registered.");
			}
		} else if(sender instanceof ConsoleCommandSender) {
			ServerCapitalUser user = CapitalUserFactory.getServerUser();
			user.sendMessage("§8You have §6$" + user.getVault().getValue() + " §8worth of items in your vault, consisting of " + user.getVault().getTypeAmounts());
		}
	}
	
}
