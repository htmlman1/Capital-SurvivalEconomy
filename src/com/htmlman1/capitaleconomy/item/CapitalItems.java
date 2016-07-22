package com.htmlman1.capitaleconomy.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.htmlman1.capitaleconomy.lottery.LotteryTicketManager;
import com.htmlman1.capitaleconomy.util.ValidationUtils;
import com.htmlman1.capitaleconomy.lottery.LotteryFullException;

public class CapitalItems {

	public static ItemStack getLotteryTicket() throws LotteryFullException {
		ItemStack ticket = new ItemStack(Material.PAPER);
		ItemMeta ticketMeta = ticket.getItemMeta();
		ticketMeta.setDisplayName(ChatColor.GREEN+"Lottery Ticket");
		
		int ticketNumber;
		ticketNumber = LotteryTicketManager.getNextLotteryNumber();
		
		ticketMeta.setLore(Arrays.asList(String.valueOf(ticketNumber), "�9Server Powerball"));
		return ticket;
	}
	
	public static boolean isLotteryTicket(ItemStack i) {
		if(i != null && i.hasItemMeta()) {
			ItemMeta im = i.getItemMeta();
			if(im.hasDisplayName() && im.hasLore()) {
				if(im.getDisplayName().equals(ChatColor.GREEN+"Lottery Ticket")) {
					List<String> lore = im.getLore();
					if(ValidationUtils.isInt(lore.get(0))) {
						if(lore.get(1).equals("�9Server Powerball")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static int getTicketNumber(ItemStack i) {
		if(isLotteryTicket(i)) {
			return Integer.parseInt(i.getItemMeta().getLore().get(0));
		}
		return -1;
	}
	
}
