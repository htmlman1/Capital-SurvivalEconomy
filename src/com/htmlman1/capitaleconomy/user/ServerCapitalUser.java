package com.htmlman1.capitaleconomy.user;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.htmlman1.capitaleconomy.CapitalEconomy;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.vault.CapitalVault;

public class ServerCapitalUser {
	
	private double lottery;
	private double market;
	private CapitalVault vault;
	
	public ServerCapitalUser(ConfigurationSection s) {
		double lottery = s.getDouble("userdata.lottery");
		double market = s.getDouble("userdata.market");
		
		Map<String, Object> vaultValues = s.getConfigurationSection("userdata.vault").getValues(true);
		CapitalVault vault = CapitalVault.deserialize(vaultValues);
		
		this.setLottery(lottery);
		this.setMarket(market);
		this.setVault(vault);
	}
	
	public ServerCapitalUser(double lottery, double market, CapitalVault vault) {
		this.lottery = lottery;
		this.market = market;
		this.vault = (vault == null) ? new CapitalVault(null) : vault;
	}
	
	public File getDataFile() {
		return new File(CapitalEconomy.usersDir + File.separator + "SERVER.yml");
	}

	public boolean hasPermission(CapitalPermission permission) {
		return true;
	}
	
	public void save() throws IOException {
		Map<String, Object> serialized = new HashMap<String, Object>();
		serialized.put("uuid", "%SERVER%");
		serialized.put("lottery", this.getLottery());
		serialized.put("market", this.getMarket());
		serialized.put("vault", this.getVault().serialize());
		FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(this.getDataFile());
		dataConfig.createSection("userdata", serialized);
		dataConfig.save(this.getDataFile());
	}
	
	public boolean isBankrupt(AccountType accountType) {
		switch(accountType) {
		case LOTTERY:
			return this.getLottery() <= 0;
		case MARKET:
			return this.getMarket() <= 0;
		default:
			return false;
		}
	}
	
	public double getBalance(AccountType accountType) {
		switch(accountType) {
		case LOTTERY:
			return this.getLottery();
		case MARKET:
			return this.getMarket();
		default:
			return 0d;
		}
	}
	
	public void setBalance(AccountType accountType, double amount) {
		switch(accountType) {
		case LOTTERY:
			this.setLottery(amount);
			break;
		case MARKET:
			this.setMarket(amount);
			break;
		default:
			break;
		}
	}
	
	public void changeBalance(AccountType accountType, double amount) {
		this.setBalance(accountType, this.getBalance(accountType) + amount);
	}
	
	public void decreaseBalance(AccountType accountType, double amount) {
		this.changeBalance(accountType, -amount);
	}
	
	public void increaseBalance(AccountType accountType, double amount) {
		this.changeBalance(accountType, amount);
	}
	
	public boolean canAfford(double amount, AccountType accountType) {
		if(this.isBankrupt(accountType)) return false; else return true;
	}
	
	public void pay(CapitalUser payee, AccountType accountType, double amount) {
		this.decreaseBalance(accountType, amount);
		payee.increaseDebit(amount);
	}
	
	public void sendMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(message);
	}

	private double getLottery() {
		return lottery;
	}

	private void setLottery(double lottery) {
		this.lottery = lottery;
	}

	private double getMarket() {
		return market;
	}

	private void setMarket(double market) {
		this.market = market;
	}

	public CapitalVault getVault() {
		return vault;
	}

	public void setVault(CapitalVault vault) {
		this.vault = vault;
	}
	
	public enum AccountType {
		LOTTERY, MARKET
	}
	
}
