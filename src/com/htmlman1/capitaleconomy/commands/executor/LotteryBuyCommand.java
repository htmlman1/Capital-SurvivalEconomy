package com.htmlman1.capitaleconomy.commands.executor;

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
						sender.sendMessage("§aYou purchased one lottery ticket for §6$" + ConfigurationSettings.ticketPrice + "§a.");
					} catch (LotteryFullException e) {
						throw new IllegalArgumentException("§cSorry, but all available slots for the lottery have already been purchased.");
					}
				} else {
					throw new IllegalArgumentException("§cPlease empty a slot in your inventory first.");
				}
			} else {
				throw new IllegalArgumentException(CapitalMessages.NO_PERMS);
			}
		} else {
			throw new IllegalArgumentException(CapitalMessages.BE_PLAYER);
		}
	}
	
}
