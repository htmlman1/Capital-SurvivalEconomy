package com.htmlman1.capitaleconomy.commands.handler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.CapitalEconomy;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;

public class CapitalAdminCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if(sender.hasPermission(CapitalPermission.RELOAD_CONFIG.getApplicablePermission())) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("reload")) {
					CapitalEconomy.loadValues();
					sender.sendMessage(ChatColor.AQUA + "All datafiles reloaded.");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
		}
		return true;
	}

}
