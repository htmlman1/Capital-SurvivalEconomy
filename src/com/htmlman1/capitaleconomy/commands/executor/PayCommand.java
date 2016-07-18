package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;

public class PayCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		try {
			UserPayCommand.execute(sender, args);
		} catch (IllegalArgumentException e) {
			sender.sendMessage(e.getMessage());
		}
		
		CapitalUserFactory.save();
		return true;
	}

}
