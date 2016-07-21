package com.htmlman1.capitaleconomy;

import java.io.File;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.htmlman1.capitaleconomy.api.CapitalUserFactory;
import com.htmlman1.capitaleconomy.bank.ServerShop;
import com.htmlman1.capitaleconomy.commands.executor.PayCommand;
import com.htmlman1.capitaleconomy.commands.handler.CapitalAdminCommands;
import com.htmlman1.capitaleconomy.commands.handler.CashCommands;
import com.htmlman1.capitaleconomy.commands.handler.DebitCommands;
import com.htmlman1.capitaleconomy.commands.handler.LotteryCommands;
import com.htmlman1.capitaleconomy.commands.handler.PayWithCommand;
import com.htmlman1.capitaleconomy.commands.handler.ShopCommands;
import com.htmlman1.capitaleconomy.commands.handler.VaultCommands;
import com.htmlman1.capitaleconomy.configuration.ConfigurationSettings;
import com.htmlman1.capitaleconomy.listeners.GeneralListener;
import com.htmlman1.capitaleconomy.listeners.SignShopListener;
import com.htmlman1.capitaleconomy.listeners.VaultRegisterListener;
import com.htmlman1.capitaleconomy.lottery.LotteryTicketManager;

public class CapitalEconomy extends JavaPlugin {
	
	public static Plugin plugin;
	public static File usersDir;
	public static File purchaseFile;

	@Override
	public void onEnable() {
		plugin = this;
		this.saveDefaultConfig();
		this.saveResource("purchases.yml", false);
		this.saveResource("signs.yml", false);

		
		usersDir = new File(this.getDataFolder() + File.separator + "users");
		purchaseFile = new File(this.getDataFolder() + File.separator + "purchases.yml");
		
		createDirectories(usersDir);
		
		this.getCommand("capital").setExecutor(new CapitalAdminCommands()); // TODO
		this.getCommand("cash").setExecutor(new CashCommands());
		this.getCommand("debit").setExecutor(new DebitCommands());
		this.getCommand("lotto").setExecutor(new LotteryCommands());
		this.getCommand("pay").setExecutor(new PayCommand());
		this.getCommand("paywith").setExecutor(new PayWithCommand());
		this.getCommand("shop").setExecutor(new ShopCommands()); // TODO
		this.getCommand("vault").setExecutor(new VaultCommands());
		
		this.getServer().getPluginManager().registerEvents(new GeneralListener(), this);
		this.getServer().getPluginManager().registerEvents(new SignShopListener(this), this);
		this.getServer().getPluginManager().registerEvents(new VaultRegisterListener(), this);
		
		ConfigurationSettings.init(this.getConfig());
		ServerShop.init(this.getConfig());
		CapitalUserFactory.init(usersDir);
		LotteryTicketManager.init(purchaseFile);
	}
	
	@Override
	public void onDisable() {
		plugin = null;
	}
	
	private void createDirectories(File... files) {
		for(File f : files) {
			if(!f.exists()) f.mkdir();
		}
	}
	
}
