package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.user.ServerCapitalUser;
import com.htmlman1.capitaleconomy.user.ServerCapitalUser.AccountType;

public class DebitCheckCommand {

	public static void execute(CommandSender sender) throws IllegalArgumentException {
		if(sender instanceof Player) {
			CapitalUser user = CapitalUserFactory.getUser((Player) sender);
			user.sendMessage("§7Current debit balance: §6$" + user.getDebit());
		} else if(sender instanceof ConsoleCommandSender) {
			ServerCapitalUser user = CapitalUserFactory.getServerUser();
			
			double lottery = user.getBalance(AccountType.LOTTERY);
			double market = user.getBalance(AccountType.MARKET);
			
			sender.sendMessage("§bCurrent server balance:");
			sender.sendMessage("§b-----------------------");
			sender.sendMessage("§7Lottery balance: §6$" + lottery);
			sender.sendMessage("§7Market balance: §6$" + market);
		}
	}
	
}
