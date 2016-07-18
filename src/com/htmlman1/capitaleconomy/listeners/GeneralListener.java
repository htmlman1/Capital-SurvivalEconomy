package com.htmlman1.capitaleconomy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.Sign;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.commands.executor.CapitalMessages;
import com.htmlman1.capitaleconomy.event.VaultRegisterEvent;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.util.VaultUtils;

public class GeneralListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Block placed = event.getBlock();
		if(((Sign) placed.getState().getData()).isWallSign()) {
			Block against = VaultUtils.getAttached((org.bukkit.block.Sign) placed.getState());
			if(ChatColor.stripColor(event.getLine(0)).equals("[CapitalVault]")) {
				Player player = event.getPlayer();
				if(VaultUtils.isDoubleChest(against)) {
					if(player.hasPermission(CapitalPermission.CHECK_THEIR_VAULT.getApplicablePermission()) || player.hasPermission(CapitalPermission.CHECK_ALL_VAULT.getApplicablePermission())) {
						CapitalUser user = CapitalUserFactory.getUser(player);
						if(!user.hasVault()) {
							Bukkit.getPluginManager().callEvent(new VaultRegisterEvent(player.getUniqueId(), VaultUtils.toDoubleChest(against)));
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
