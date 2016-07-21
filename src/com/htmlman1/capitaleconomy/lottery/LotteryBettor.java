package com.htmlman1.capitaleconomy.lottery;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.htmlman1.capitaleconomy.item.CapitalItems;

public class LotteryBettor {
	
	private UUID playerID;

	public LotteryBettor(Player p) {
		this.playerID = p.getUniqueId();
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(this.playerID);
	}
	
	public UUID getUniqueId() {
		return this.playerID;
	}
	
	public boolean isPlayer() {
		return this.getPlayer() != null;
	}
	
	public ItemStack getTicket() {
		if(this.hasTicket()) {
			Inventory inv = this.getPlayer().getInventory();
			return inv.getItem(this.getTicketIndex());
		}
		return null;
	}
	
	private int getTicketIndex() {
		Inventory inv = this.getPlayer().getInventory();
		if(this.isPlayer()) {
			for(int i = 0; i < inv.getContents().length; i++) {
				ItemStack item = inv.getItem(i);
				if(item != null && CapitalItems.isLotteryTicket(item)) return i;
			}
		}
		return -1;
	}
	
	public int getTicketNumber() {
		if(this.hasTicket()) {
			return CapitalItems.getTicketNumber(this.getTicket());
		}
		return -1;
	}
	
	public boolean hasTicket() {
		return getTicketIndex() > -1;
	}
	
	public void removeTicket() {
		if(this.hasTicket()) {
			Inventory inv = this.getPlayer().getInventory();
			inv.remove(this.getTicket());
		}
	}
	
}
