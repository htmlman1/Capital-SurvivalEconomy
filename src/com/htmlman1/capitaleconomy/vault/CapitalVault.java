package com.htmlman1.capitaleconomy.vault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.htmlman1.capitaleconomy.bank.ServerShop;

public class CapitalVault {
	
	private List<ItemStack> contents;

	public CapitalVault(List<ItemStack> contents) {
		this.contents = (contents == null) ? new ArrayList<ItemStack>() : contents;
	}
	
	public void addItem(ItemStack i) {
		if(!contents.isEmpty()) {
			Material additionMaterial = i.getType();
			int additionAmount = i.getAmount();
			
			for(ItemStack content : contents) {
				if(content.getType() == additionMaterial) {
					content.setAmount(content.getAmount() + additionAmount);
					return;
				}
			}
		}
		
		contents.add(i);
	}
	
	public int getAmount(Material type) {
		if(!contents.isEmpty()) {
			for(ItemStack content : contents) {
				if(content.getType() == type) return content.getAmount();
			}
		}
		return 0;
	}
	
	public String getTypeAmounts() {
		if(!contents.isEmpty()) {
			StringBuilder result = new StringBuilder();
			Map<Material, Integer> amounts = new HashMap<Material, Integer>();
			int matAmount = 0;
			if(ServerShop.hasBackedMaterials()) {
				for(Material m : ServerShop.getBackedMaterials()) {
					for(ItemStack content : contents) {
						if(content != null && content.getType() == m) {
							matAmount += content.getAmount();
						}
					}
					amounts.put(m, matAmount);
					matAmount = 0;
				}
			}
			
			for(Map.Entry<Material, Integer> amount : amounts.entrySet()) {
				if(amount.getValue() > 0) result.append(ChatColor.GOLD + amount.getValue().toString() + " " + amount.getKey() + ChatColor.BLUE+", ");
			}
			
			return (amounts.size() > 1) ? result.toString().substring(0, result.toString().length() - 2) : result.toString();
		}
		return "";
	}
	
	public double getValue() {
		return ServerShop.getValue(this.contents);
	}
	
	public void remove(Material type, int amount) {
		if(!contents.isEmpty()) {
			Iterator<ItemStack> contentIterator = contents.iterator();
			while(contentIterator.hasNext()) {
				ItemStack content = contentIterator.next();
				if(content.getType() == type) {
					int currentAmount = content.getAmount();
					
					if((currentAmount - amount) <= 0) {
						contentIterator.remove();
					} else {
						content.setAmount(currentAmount - amount);
					}
				}
			}
		}
	}
	
	public void removeAll(Material type) {
		if(!contents.isEmpty()) {
			Iterator<ItemStack> contentIterator = contents.iterator();
			while(contentIterator.hasNext()) {
				if(contentIterator.next().getType() == type) contentIterator.remove();
			}
		}
	}
	
	public List<ItemStack> getContents() {
		return this.contents;
	}
	
	@SuppressWarnings("unchecked")
	public static CapitalVault deserialize(Map<String, Object> map) {
		if(map != null && !map.isEmpty()) {
			List<ItemStack> deserialized = new ArrayList<ItemStack>();
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				deserialized.add(ItemStack.deserialize((Map<String, Object>) entry.getValue()));
			}
			return new CapitalVault(deserialized);
		}
		return new CapitalVault(null);
	}
	
	public Map<String, Object> serialize() {
		Map<String, Object> serialized = new HashMap<String, Object>();
		
		if(!contents.isEmpty()) {
			for(int i = 0; i < contents.size(); i++) {
				serialized.put(String.valueOf(i), contents.get(i).serialize());
			}
		}
		
		return serialized;
	}
	
}
