package com.htmlman1.capitaleconomy.commands.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.commands.executor.VaultBalanceCommand;

public class VaultCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		try {
			VaultBalanceCommand.execute(sender);
		} catch (IllegalArgumentException e) {
			sender.sendMessage(e.getMessage());
		}
		return true;
	}
	
}
