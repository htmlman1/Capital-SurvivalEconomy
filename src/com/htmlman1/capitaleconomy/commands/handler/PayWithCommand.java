package com.htmlman1.capitaleconomy.commands.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.commands.executor.PayPrefCommand;

public class PayWithCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		try {
			PayPrefCommand.execute(sender, args);
		} catch (IllegalArgumentException e) {
			sender.sendMessage(e.getMessage());
		}
		return true;
	}

}
