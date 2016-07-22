package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.ChatColor;
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
			user.sendMessage(ChatColor.DARK_PURPLE+"Current debit balance:"+ ChatColor.GOLD + user.getDebit());
		} else if(sender instanceof ConsoleCommandSender) {
			ServerCapitalUser user = CapitalUserFactory.getServerUser();
			
			double lottery = user.getBalance(AccountType.LOTTERY);
			double market = user.getBalance(AccountType.MARKET);
			
			sender.sendMessage(ChatColor.BLUE+"Current server balance:");
			sender.sendMessage(ChatColor.BLUE+"-----------------------");
			sender.sendMessage(ChatColor.DARK_PURPLE+"Lottery balance: "+ChatColor.GOLD+"$" + lottery);
			sender.sendMessage(ChatColor.DARK_PURPLE+"Market balance:"+ChatColor.GOLD+"$" + market);
		}
	}
	
}
