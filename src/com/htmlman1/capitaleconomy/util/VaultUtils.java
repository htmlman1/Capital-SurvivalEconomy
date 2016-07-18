package com.htmlman1.capitaleconomy.util;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VaultUtils {
	
	public static Block getAttached(Sign sign) {
		if(isAttached(sign)) {
			return sign.getBlock().getRelative(((org.bukkit.material.Sign) sign.getData()).getAttachedFace());
		}
		return null;
	}
	
	public static boolean isAttached(Sign sign) {
		return ((org.bukkit.material.Sign) sign.getData()).isWallSign();
	}

	public static boolean isDoubleChest(Block block) {
		if(block.getState() instanceof Chest) {
			Inventory inv = ((Chest) block.getState()).getInventory();
			return inv.getHolder() instanceof DoubleChest;
		}
		return false;
	}
	
	public static boolean isEmpty(Inventory inventory) {
		ItemStack[] contents = inventory.getContents();
		if(contents != null) {
			for(ItemStack content : contents) {
				if(content != null) return false;
			}
		}
		return true;
	}
	
	public static boolean isVaultSign(Block block) {
		if(block.getState() instanceof Sign) {
			Sign sign = (Sign) block.getState();
			return ChatColor.stripColor(sign.getLine(0)).trim().equals("[CapitalVault]");
		}
		return false;
	}
	
	public static DoubleChest toDoubleChest(Block block) {
		if(isDoubleChest(block)) {
			return (DoubleChest) ((Chest) block.getState()).getInventory().getHolder();
		}
		return null;
	}
	
}
