package com.htmlman1.capitaleconomy.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.htmlman1.capitaleconomy.user.CapitalUser;
import com.htmlman1.capitaleconomy.user.ServerCapitalUser;

public class CapitalUserFactory {
	
	private static ServerCapitalUser serveruser = new ServerCapitalUser(0d, 0d, null);
	private static List<CapitalUser> users = new ArrayList<CapitalUser>();

	private static CapitalUser createUser(UUID uuid) {
		if(!isUser(uuid)) {
			CapitalUser user = new CapitalUser(uuid, 0d);
			users.add(user);
			return user;
		} else {
			return getUser(uuid);
		}
	}
	
	public static ServerCapitalUser getServerUser() {
		return serveruser;
	}
	
	public static CapitalUser getUser(Player p) {
		return getUser(p.getUniqueId());
	}
	
	@SuppressWarnings("deprecation")
	public static CapitalUser getUser(String username) {
		return getUser(Bukkit.getPlayer(username));
	}

	public static CapitalUser getUser(UUID uuid) {
		if(!users.isEmpty()) {
			if(isUser(uuid)) {
				for(CapitalUser user : users) {
					if(user.getUUID().equals(uuid)) {
						return user;
					}
				}
			}
		}
		return createUser(uuid);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isOnline(String name) {
		Player player = Bukkit.getPlayer(name);
		if(player == null) return false;
		return player.isOnline();
	}
	
	public static boolean isOnline(UUID id) {
		Player player = Bukkit.getPlayer(id);
		if(player == null) return false;
		return player.isOnline();
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isUser(String name) {
		Player player = Bukkit.getPlayer(name);
		if(player == null) return false;
		else return isUser(player.getUniqueId());
	}
	
	public static boolean isUser(UUID uuid) {
		if(!users.isEmpty()) {
			for(CapitalUser user : users) {
				if(user.getUUID().equals(uuid)) return true;
			}
		}
		return false;
	}
	
	public static void updateUser(UUID id, CapitalUser newUser) {
		if(!users.isEmpty()) {
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUUID().equals(id)) {
					users.set(i, newUser);
				}
			}
		}
	}
	
	public static void init(File userDir) {
		if(userDir.listFiles() == null) return;
		for(File userFile : userDir.listFiles()) {
			FileConfiguration loadedConfiguration = YamlConfiguration.loadConfiguration(userFile);
			if(loadedConfiguration.getString("userdata.uuid").equals("%SERVER%")) {
				serveruser = new ServerCapitalUser(loadedConfiguration);
			} else {
				users.add(new CapitalUser(loadedConfiguration));
			}
		}
	}
	
	public static void save() {
		if(!users.isEmpty()) {
			for(CapitalUser user : users) {
				try {
					user.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
