package com.htmlman1.capitaleconomy.user;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.htmlman1.capitaleconomy.CapitalEconomy;
import com.htmlman1.capitaleconomy.configuration.ConfigurationSettings;
import com.htmlman1.capitaleconomy.money.util.PaymentType;
import com.htmlman1.capitaleconomy.perms.CapitalPermission;
import com.htmlman1.capitaleconomy.util.VaultUtils;
import com.htmlman1.capitaleconomy.vault.CapitalVault;

public class CapitalUser {

	private double debit;
	private UUID uuid;
	private DoubleChest vault;
	private PaymentType paymentType;
	private boolean hasShop;
	
	public CapitalUser(ConfigurationSection s) {
		if(s == null) throw new IllegalArgumentException("Invalid ConfigurationSection supplied.");
		this.setUUID(UUID.fromString(s.getString("userdata.uuid")));
		this.setDebit(s.getDouble("userdata.debit"));
		
		Location vaultLoc = null;
		if(s.isSet("userdata.vault.world")) {
			vaultLoc = Location.deserialize(s.getConfigurationSection("userdata.vault").getValues(true));
		}
		
		if(vaultLoc != null) {
			Block vaultBlock = vaultLoc.getBlock();
			if(vaultBlock != null) {
				if(vaultBlock.getState() instanceof Chest) {
					Chest vaultChest = (Chest) vaultBlock.getState();
					if(vaultChest.getInventory().getHolder() instanceof DoubleChest) {
						this.vault = (DoubleChest) vaultChest.getInventory().getHolder();
					}
				}
			}
		}
		
		this.setHasShop(s.getBoolean("userdata.has-shop"));
		this.setPaymentType(PaymentType.valueOf(s.getString("userdata.paymenttype")));
	}
	
	public CapitalUser(UUID uuid, double debit) {
		this.setUUID(uuid);
		this.setDebit(debit);
		this.setPaymentType(ConfigurationSettings.defaultPayment);
		this.setHasShop(false);
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}
	
	public boolean isBankrupt() {
		return this.debit <= 0;
	}
	
	public void changeDebit(double amount) {
		this.setDebit(this.getDebit() + amount);
	}
	
	public void decreaseDebit(double amount) {
		this.changeDebit(-Math.abs(amount));
	}
	
	public void increaseDebit(double amount) {
		this.changeDebit(Math.abs(amount));
	}
	
	private File getDataFile() {
		return new File(CapitalEconomy.usersDir + File.separator + this.getUUID() + ".yml");
	}

	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	
	public CapitalVault getCapitalVault() {
		if(this.hasVault()) {
			if(VaultUtils.isEmpty(this.vault.getInventory())) {
				return new CapitalVault(null);
			}
			return new CapitalVault(Arrays.asList(this.vault.getInventory().getContents()));
		}
		return null;
	}
	
	public DoubleChest getVault() {
		return vault;
	}
	
	public boolean hasVault() {
		return vault != null;
	}

	public void setVault(DoubleChest vault) {
		this.vault = vault;
	}
	
	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public boolean hasShop() {
		return hasShop;
	}

	public void setHasShop(boolean hasShop) {
		this.hasShop = hasShop;
	}

	public boolean canAfford(double amount) {
		if(this.isBankrupt()) return false; else return this.getDebit() >= amount;
	}
	
	public String getDisplayName() {
		Player player = Bukkit.getPlayer(this.getUUID());
		if(player != null) return player.getDisplayName(); else return "";
	}
	
	public boolean hasPermission(CapitalPermission permission) {
		Player player = Bukkit.getPlayer(this.getUUID());
		if(player.isOp()) return true;
		return player.hasPermission(permission.getApplicablePermission());
	}
	
	public boolean isOnline() {
		Player player = Bukkit.getPlayer(this.getUUID());
		return (player != null) ? player.isOnline() : false;
	}
	
	public void pay(CapitalUser payee, double amount) {
		this.decreaseDebit(Math.abs(amount));
		payee.increaseDebit(Math.abs(amount));
	}

	public void sendMessage(String message) {
		Player player = Bukkit.getPlayer(this.getUUID());
		if(player != null && player.isOnline()) {
			player.sendMessage(message);
		}
	}
	
	public Map<String, Object> serialize() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("uuid", this.getUUID().toString());
		result.put("debit", this.getDebit());
		if(this.hasVault()) {
			result.put("vault", vault.getLocation().serialize());
		} else {
			result.put("vault", new HashMap<String, Object>());
		}
		result.put("has-shop", this.hasShop());
		result.put("paymenttype", this.getPaymentType().name());
		return result;
	}
	
	public void save() throws IOException {
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(this.getDataFile());
		yaml.createSection("userdata", this.serialize());
		yaml.save(this.getDataFile());
	}
	
}
