package com.htmlman1.capitaleconomy.listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
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

	@EventHandler
	public void onVaultRegister(VaultRegisterEvent event) {
		if(!event.isCancelled()) {
			CapitalUser user = CapitalUserFactory.getUser(event.getOwner());
			user.setVault(event.getVault());
			
			Bukkit.getScheduler().runTaskLater(CapitalEconomy.plugin, new Runnable() {
				public void run() {
					Sign vaultSign = event.getVaultSign();
					vaultSign.setLine(0, "§3[§6CapitalVault§3]");
					vaultSign.setLine(1, user.getPlayer().getName());
					vaultSign.update();
				}
			}, 1l);
			
			user.sendMessage("§aYou have registered a new vault.");
			
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
		
		owner.sendMessage("§cYour vault was unregistered.");
		
		try {
			owner.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
