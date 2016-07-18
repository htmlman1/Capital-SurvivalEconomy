package com.htmlman1.capitaleconomy.shop;

import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;

import com.htmlman1.capitaleconomy.user.CapitalUser;

public class CapitalShop {
	
	private CapitalUser owner;
	private List<ShopSign> signs;
	
	public CapitalShop(ConfigurationSection s) {
		
	}

	public CapitalShop(UUID owner, List<ShopSign> signs) {
		
	}

	public CapitalUser getOwner() {
		return owner;
	}

	public void setOwner(CapitalUser owner) {
		this.owner = owner;
	}

	public List<ShopSign> getSigns() {
		return signs;
	}

	public void setSigns(List<ShopSign> signs) {
		this.signs = signs;
	}
	
}
