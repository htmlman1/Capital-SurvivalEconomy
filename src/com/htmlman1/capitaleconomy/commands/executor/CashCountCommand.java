package com.htmlman1.capitaleconomy.commands.executor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.htmlman1.capitaleconomy.bank.ServerShop;

public class CashCountCommand {

	public static void execute(CommandSender sender, String[] args) throws IllegalArgumentException {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			double worth = 0d;
			if(args.length >= 1) {
				String context = "inventory";
				if(args[0].equalsIgnoreCase("hand")) {
					context = "hand";
					ItemStack hand = player.getInventory().getItemInMainHand();
					worth = ServerShop.getValue(hand);
				} else {
					worth = ServerShop.getValue(player.getInventory());
				}
				
				sender.sendMessage(ChatColor.GREEN+"The items in your " + context + " are currently worth ChatColor.GOLD+"$" + CapitalMessages.toCashFormat(worth) + ChatColor.GREEN+".");
			} else {
				sender.sendMessage(ChatColor.RED+"/cash <all|hand>");
			}
		} else {
			throw new IllegalArgumentException(CapitalMessages.BE_PLAYER);
		}
	}
	
}
