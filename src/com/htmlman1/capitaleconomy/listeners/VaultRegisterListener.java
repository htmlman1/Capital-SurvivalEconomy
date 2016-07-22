package com.htmlman1.capitaleconomy.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.htmlman1.capitaleconomy.CapitalEconomy;
import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.event.VaultRegisterEvent;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent;
import com.htmlman1.capitaleconomy.event.VaultUnregisterEvent.RemovalMethod;
import com.htmlman1.capitaleconomy.user.CapitalUser;

public class VaultRegisterListener implements Listener {

	CapitalEconomy plugin;
	
	public VaultRegisterListener(CapitalEconomy plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onVaultRegister(final VaultRegisterEvent event) {
		if(!event.isCancelled()) {
			final CapitalUser user = CapitalUserFactory.getUser(event.getOwner());
			user.setVault(event.getVault());
			
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					Sign vaultSign = event.getVaultSign();
					vaultSign.setLine(0, ChatColor.DARK_AQUA + "[" + ChatColor.GOLD + "CapitalVault" + ChatColor.DARK_AQUA + "]");
					vaultSign.setLine(1, user.getPlayer().getName());
					vaultSign.update();
				}
			}, 1l);
			
			user.sendMessage(ChatColor.GREEN+"You have registered a new vault.");
			
			try {
				user.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onVaultUnregister(VaultUnregisterEvent event) {
		if(event.isCancelled() && event.getRemovalMethod() != RemovalMethod.COMMAND) return;
			
		CapitalUser owner = CapitalUserFactory.getUser(event.getOwner());
		owner.removeVault();
		
		owner.sendMessage(ChatColor.RED+"Your vault was unregistered.");
		
		try {
			owner.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
