package com.htmlman1.capitaleconomy.lottery;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.htmlman1.capitaleconomy.item.CapitalItems;

// A convenience class for managing players with lottery tickets in their inventories.
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
	
	public boolean isValid() {
		return this.getPlayer() != null;
	}
	
	public boolean hasTicket() {
		if(this.isValid()) {
			for(ItemStack i : this.getPlayer().getInventory().getContents()) {
				if(i != null && CapitalItems.isLotteryTicket(i)) return true;
			}
		}
		return false;
	}
	
}
