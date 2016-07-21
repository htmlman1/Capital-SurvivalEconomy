package com.htmlman1.capitaleconomy.lottery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.htmlman1.capitaleconomy.CapitalEconomy;

public class LotteryTicketManager {
	
	private static Map<UUID, Integer> ticketPurchases = new HashMap<UUID, Integer>();
	private static List<Integer> registeredNumbers = new ArrayList<Integer>();
	private static Random rand = new Random();
	
	public static void addPurchase(UUID id) {
		if(!hasPurchased(id)) {
			ticketPurchases.put(id, 1);
			return;
		}
		
		if(canPurchase(id)) {
			int current = getPurchases(id);
			ticketPurchases.put(id, current + 1);
		}
	}
	
	public static boolean canPurchase(UUID id) {
		return getPurchases(id) < 10;
	}
	
	public static int getPurchases(UUID id) {
		return ticketPurchases.get(id);
	}
	
	public static boolean hasPurchased(UUID id) {
		return getPurchases(id) > 0;
	}
	
	public static boolean hasPurchases() {
		return !ticketPurchases.isEmpty();
	}
	
	public static void clearPurchases(UUID id) {
		ticketPurchases.put(id, 0);
	}
	
	public static void init(File f) {
		FileConfiguration purchaseConfiguration = YamlConfiguration.loadConfiguration(f);
		
		if(purchaseConfiguration.isSet("purchases")) {
			Map<String, Object> purchases = purchaseConfiguration.getConfigurationSection("purchases").getValues(true);
			for(Map.Entry<String, Object> purchase : purchases.entrySet()) {
				ticketPurchases.put(UUID.fromString(purchase.getKey()), (Integer) purchase.getValue());
			}
		}
		
		if(purchaseConfiguration.isSet("numbers")) {
			List<String> numbers = purchaseConfiguration.getStringList("numbers");
			for(String n : numbers) {
				registeredNumbers.add(Integer.parseInt(n));
			}
		}
	}
	
	public static void clearLottery() {
		registeredNumbers.clear();
	}
	
	public static int getNextLotteryNumber() throws LotteryFullException {
		if(registeredNumbers.size() < 100) {
			int nextInt = rand.nextInt(100) + 1;
			
			while(registeredNumbers.contains(nextInt)) {
				nextInt = rand.nextInt(100) + 1;
			}
			
			registeredNumbers.add(nextInt);
			return nextInt;
		}
		
		throw new LotteryFullException();
	}
	
	public static void save() throws IOException {
		FileConfiguration purchaseConfiguration = YamlConfiguration.loadConfiguration(CapitalEconomy.purchaseFile);
		purchaseConfiguration.set("purchases", null);
		purchaseConfiguration.set("numbers", registeredNumbers);
		
		if(hasPurchases()) {
			for(Map.Entry<UUID, Integer> purchase : ticketPurchases.entrySet()) {
				purchaseConfiguration.set("purchases." + purchase.getKey().toString(), purchase.getValue());
			}
		}
		
		purchaseConfiguration.save(CapitalEconomy.purchaseFile);
	}
	
}
