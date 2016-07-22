package com.htmlman1.capitaleconomy.commands.handler;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.commands.executor.LotteryBuyCommand;
import com.htmlman1.capitaleconomy.commands.executor.LotteryCheckCommand;
import com.htmlman1.capitaleconomy.lottery.LotteryTicketManager;

public class LotteryCommands implements CommandExecutor {
	
	private static final String HELP = ChatColor.RED+"/lottery <bal|buy>";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		try {
			if(args.length == 1) {
				switch(args[0].toLowerCase()) {
				case "bal":
					LotteryCheckCommand.execute(sender);
					break;
				case "buy":
					LotteryBuyCommand.execute(sender);
					break;
				default:
					sender.sendMessage(HELP);
				}
			} else {
				sender.sendMessage(HELP);
			}
		} catch (IllegalArgumentException e) {
			sender.sendMessage(e.getMessage());
		}
		
		CapitalUserFactory.save();
		try {
			LotteryTicketManager.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
