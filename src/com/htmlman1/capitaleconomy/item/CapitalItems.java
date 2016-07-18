package com.htmlman1.capitaleconomy.item;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.htmlman1.capitaleconomy.lottery.CapitalLotteryManager;
import com.htmlman1.capitaleconomy.lottery.LotteryFullException;

public class CapitalItems {

	public static ItemStack getLotteryTicket() throws LotteryFullException {
		ItemStack ticket = new ItemStack(Material.PAPER);
		ItemMeta ticketMeta = ticket.getItemMeta();
		ticketMeta.setDisplayName("§aLottery Ticket");
		
		int ticketNumber;
		ticketNumber = CapitalLotteryManager.getNextLotteryNumber();
		
		ticketMeta.setLore(Arrays.asList(String.valueOf(ticketNumber), "§9Server Powerball"));
		return ticket;
	}
	
}
