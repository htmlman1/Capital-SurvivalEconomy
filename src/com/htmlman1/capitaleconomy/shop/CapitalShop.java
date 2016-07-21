package com.htmlman1.capitaleconomy.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.htmlman1.capitaleconomy.util.Serialization;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

import com.htmlman1.capitaleconomy.user.CapitalUser;

public class CapitalShop {

	private CapitalUser owner;
	private List<ShopSign> signs = new ArrayList<ShopSign>();//Fix NPE
	ConfigurationSection s;

	public CapitalShop(ConfigurationSection s) {
		this.s = s;
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

	public void init() {
        for(String locString : s.getStringList("Locations")){
            getSigns().add(new ShopSign((Sign)Serialization.fromLocationString(locString).getBlock().getState()));
        }
	}
}
