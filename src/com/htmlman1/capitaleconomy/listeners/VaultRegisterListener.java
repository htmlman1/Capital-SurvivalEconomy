package com.htmlman1.capitaleconomy.listeners;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.event.VaultRegisterEvent;
import com.htmlman1.capitaleconomy.user.CapitalUser;

public class VaultRegisterListener implements Listener {

	@EventHandler
	public void onVaultRegister(VaultRegisterEvent event) {
		if(!event.isCancelled()) {
			CapitalUser user = CapitalUserFactory.getUser(event.getOwner());
			user.setVault(event.getVault());
			user.sendMessage("§aYou have registered a new vault.");
			
			try {
				user.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
