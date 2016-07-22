package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.configuration.ConfigurationSettings;
import com.htmlman1.capitaleconomy.item.CapitalItems;
import com.htmlman1.capitaleconomy.lottery.LotteryFullException;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;

public class LotteryBuyCommand {

	public static void execute(CommandSender sender) throws IllegalArgumentException {
		if(sender instanceof Player) {
			CapitalUser user = CapitalUserFactory.getUser((Player) sender);
			if(user.hasPermission(CapitalPermission.BUY_LOTTERY)) {
				Inventory inv = ((Player) sender).getInventory();
				if(inv.firstEmpty() > -1) {
					try {
						inv.addItem(CapitalItems.getLotteryTicket());
						user.decreaseDebit(ConfigurationSettings.ticketPrice);
						sender.sendMessage(ChatColor.GREEN+"You purchased one lottery ticket for" + ChatColor.GOLD + "$" + ConfigurationSettings.ticketPrice + ChatColor.GREEN + ".");
					} catch (LotteryFullException e) {
						throw new IllegalArgumentException(ChatColor.RED+"Sorry, but all available slots for the lottery have already been purchased.");
					}
				} else {
					throw new IllegalArgumentException(ChatColor.RED+"Please empty a slot in your inventory first.");
				}
			} else {
				throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
			}
		} else {
			throw new IllegalArgumentException(CapitalMessages.BE_PLAYER);
		}
	}
	
}
