package com.htmlman1.capitaleconomy.util;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlockUtils {
	
	private static BlockFace[] chestSearchFaces = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
	
	public static Block getBlockAttachedTo(Sign sign) {
		if(isAttachedToBlock(sign)) {
			return sign.getBlock().getRelative(((org.bukkit.material.Sign) sign.getData()).getAttachedFace());
		}
		return null;
	}
	
	public static Block[] getBlockHalves(DoubleChest chest) {
		Block chestBlock = chest.getLocation().getBlock();
		for(BlockFace f : chestSearchFaces) {
			Block facedBlock = chestBlock.getRelative(f);
			if(facedBlock.getState() instanceof Chest) {
				return new Block[]{chestBlock, facedBlock};
			}
		}
		return null;
	}
	
	// Only accepts Chests as input at the moment.
	public static Sign getAttachedVaultSign(Block b) {
		if(b.getState() instanceof Chest) {
			org.bukkit.material.Chest chest = (org.bukkit.material.Chest) b.getState().getData();
			BlockFace facing = chest.getFacing();
			Block facedBlock = b.getRelative(facing);
			if(isVaultSign(facedBlock)) {
				return (Sign) facedBlock.getState();
			}
		}
		return null;
	}
	
	public static Sign getAttachedVaultSign(DoubleChest chest) {
		for(Block b : getBlockHalves(chest)) {
			if(hasVaultSignAttached(b)) return getAttachedVaultSign(b);
		}
		return null;
	}
	
	public static boolean hasVaultSignAttached(Block b) {
		return getAttachedVaultSign(b) != null;
	}
	
	public static boolean hasVaultSignAttached(DoubleChest chest) {
		for(Block b : getBlockHalves(chest)) {
			if(hasVaultSignAttached(b)) return true;
		}
		return false;
	}
	
	public static boolean isAttachedToBlock(Sign sign) {
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
	
	public static String getVaultOwner(Sign sign) {
		return sign.getLine(1);
	}
	
	public static boolean isVaultChest(Block block) {
		return BlockUtils.isDoubleChest(block) && BlockUtils.hasVaultSignAttached(BlockUtils.toDoubleChest(block));
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
