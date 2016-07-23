package com.htmlman1.capitaleconomy.commands.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.commands.executor.VaultBalanceCommand;
import com.htmlman1.capitaleconomy.commands.executor.VaultUnregisterCommand;

public class VaultCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if(args.length < 1 || !args[0].equalsIgnoreCase("unregister")) {
			try {
				VaultBalanceCommand.execute(sender);
			} catch (IllegalArgumentException e) {
				sender.sendMessage(e.getMessage());
			}
		} else {
			try {
				VaultUnregisterCommand.execute(sender);
			} catch (IllegalArgumentException e) {
				sender.sendMessage(e.getMessage());
			}
		}
		
		CapitalUserFactory.save();
		return true;
	}
	
}
