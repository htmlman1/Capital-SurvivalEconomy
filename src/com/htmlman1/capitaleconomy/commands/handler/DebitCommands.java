package com.htmlman1.capitaleconomy.commands.handler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.commands.executor.DebitCheckCommand;

public class DebitCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		DebitCheckCommand.execute(sender);
		CapitalUserFactory.save();
		return true;
	}
	
}
