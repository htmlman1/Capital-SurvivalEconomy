package com.htmlman1.capitaleconomy.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ServerShop {

	private static Map<Material, Double> backedMaterials = new HashMap<Material, Double>();
	
	public static void init(Configuration s) {
		Map<String, Object> values = s.getConfigurationSection("prices").getValues(true);
		if(values != null && !values.isEmpty()) {
			for(Map.Entry<String, Object> value : values.entrySet()) {
				backedMaterials.put(Material.getMaterial(value.getKey()), (double) value.getValue());
			}
		}
	}
	
	public static List<Material> getBackedMaterials() {
		List<Material> result = new ArrayList<Material>();
		if(hasBackedMaterials()) {
			for(Map.Entry<Material, Double> entry : backedMaterials.entrySet()) {
				result.add(entry.getKey());
			}
		}
		return result;
	}
	
	public static boolean hasBackedMaterials() {
		return !backedMaterials.isEmpty();
	}
	
	public static boolean isBacked(Material m) {
		return backedMaterials.get(m) != 0d;
	}
	
	public static double getValue(Inventory i) {
		if(i != null && i.getContents().length != 0) {
			double value = 0d;
			
			for(ItemStack item : i.getContents()) {
				value += getValue(item);
			}
			
			return value;
		}
		return 0d;
	}
	
	public static double getValue(ItemStack i) {
		int amount = i.getAmount();
		Material type = i.getType();
		
		return getValue(type) * Math.floor(amount);
	}
	
	public static double getValue(Material m) {
		return backedMaterials.get(m);
	}
	
}
