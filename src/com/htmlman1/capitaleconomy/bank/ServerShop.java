package com.htmlman1.capitaleconomy.bank;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public static double getValue(ItemStack[] items) {
		return getValue(Arrays.asList(items));
	}
	
	public static double getValue(List<ItemStack> items) {
		double value = 0d;
		
		if(items != null && items.size() > 0) {
			for(ItemStack item : items) {
				if(item != null && item.getType() != Material.AIR) value += getValue(item);
			}
		}
		
		return value;
	}
	
	public static double getValue(Inventory i) {
		return getValue(i.getContents());
	}
	
	public static double getValue(ItemStack i) {
		if(i == null || i.getType() == Material.AIR) return 0d;
		
		int amount = i.getAmount();
		Material type = i.getType();
		
		return getValue(type) * Math.floor(amount);
	}
	
	public static double getValue(Material m) {
		return backedMaterials.get(m);
	}
	
}
