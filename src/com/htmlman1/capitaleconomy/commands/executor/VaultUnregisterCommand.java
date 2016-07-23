package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent.RemovalMethod;
import com.htmlman1.capitaleconomy.user.CapitalUser;

public class VaultUnregisterCommand {

	public static void execute(CommandSender sender) throws IllegalArgumentException {
		if(sender instanceof Player) {
			CapitalUser user = CapitalUserFactory.getUser((Player) sender);
			if(user.hasVault()) {
				Bukkit.getPluginManager().callEvent(new VaultUnregisterEvent(user.getUUID(), RemovalMethod.COMMAND));
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have a vault registered!");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You must be a player to use this.");
		}
	}
	
}
