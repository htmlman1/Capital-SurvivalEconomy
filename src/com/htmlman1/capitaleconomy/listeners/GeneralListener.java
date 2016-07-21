package com.htmlman1.capitaleconomy.listeners;

import java.util.List;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.commands.executor.CapitalMessages;
import com.htmlman1.capitaleconomy.configuration.ConfigurationSettings;
import com.htmlman1.capitaleconomy.event.VaultRegisterEvent;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent.RemovalMethod;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.util.BlockUtils;

public class GeneralListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player breaker = event.getPlayer();
		Block broken = event.getBlock();
		if(BlockUtils.isVaultSign(broken)) {
			String ownerName = BlockUtils.getVaultOwner((org.bukkit.block.Sign) broken.getState());
			
			if(!breaker.getName().equals(ownerName) && !breaker.hasPermission(CapitalPermission.CHECK_ALL_VAULT.getApplicablePermission())) {
				breaker.sendMessage("§cYou don't have permission to break this.");
				event.setCancelled(true);
			} else {
				Bukkit.getPluginManager().callEvent(new VaultUnregisterEvent(CapitalUserFactory.getUser(ownerName).getUUID(), RemovalMethod.BREAK));
			}
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		boolean playClang = false;
		Location clangLocation = null;
		
		List<Block> blocks = event.blockList();
		if(blocks != null && !blocks.isEmpty()) {
			ListIterator<Block> blockIterator = blocks.listIterator();
			while(blockIterator.hasNext()) {
				Block b = blockIterator.next();
				if(BlockUtils.isVaultChest(b) || BlockUtils.isVaultSign(b)) {
					blockIterator.remove();
					playClang = true;
					clangLocation = b.getLocation();
					clangLocation.getWorld().playEffect(clangLocation, Effect.MOBSPAWNER_FLAMES, 16);
				}
			}
		}
		
		if(playClang) clangLocation.getWorld().playSound(clangLocation, Sound.BLOCK_ANVIL_LAND, 2f, 0.6f);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() != null && event.getClickedBlock().getType() != Material.AIR) {
			Block b = event.getClickedBlock();
			if(BlockUtils.isVaultChest(b)) {
				Player player = event.getPlayer();
				org.bukkit.block.Sign capitalSign = BlockUtils.getAttachedVaultSign(BlockUtils.toDoubleChest(b));
				String ownerName = BlockUtils.getVaultOwner(capitalSign);
				if(!ownerName.equals(player.getName()) && !player.hasPermission(CapitalPermission.CHECK_ALL_VAULT.getApplicablePermission())) {
					player.sendMessage("§cYou don't have permission to modify this vault!");
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if(event.getRightClicked().getType() == EntityType.VILLAGER) {
			if(ConfigurationSettings.disableTrades) {
				event.getPlayer().sendMessage("§cVillager trades are currently disabled.");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Block placed = event.getBlock();
		if(((Sign) placed.getState().getData()).isWallSign()) {
			Block against = BlockUtils.getBlockAttachedTo((org.bukkit.block.Sign) placed.getState());
			if(ChatColor.stripColor(event.getLine(0)).equals("[CapitalVault]")) {
				Player player = event.getPlayer();
				if(BlockUtils.isDoubleChest(against)) {
					if(player.hasPermission(CapitalPermission.CHECK_THEIR_VAULT.getApplicablePermission()) || player.hasPermission(CapitalPermission.CHECK_ALL_VAULT.getApplicablePermission())) {
						CapitalUser user = CapitalUserFactory.getUser(player);
						if(!user.hasVault()) {
							if(!BlockUtils.isVaultChest(against)) {
								Bukkit.getPluginManager().callEvent(new VaultRegisterEvent(player.getUniqueId(), (org.bukkit.block.Sign) placed.getState(), BlockUtils.toDoubleChest(against)));
							} else {
								placed.breakNaturally();
								player.sendMessage("§cThat chest is already registered!");
							}
						} else {
							placed.breakNaturally();
							player.sendMessage("§cYou can only have one vault.");
						}
					} else {
						placed.breakNaturally();
						player.sendMessage(CapitalMessages.NO_PERMS);
					}
				} else {
					placed.breakNaturally();
					player.sendMessage("§cThis kind of sign can only be used with double chests.");
				}
			}
		}
	}

}
