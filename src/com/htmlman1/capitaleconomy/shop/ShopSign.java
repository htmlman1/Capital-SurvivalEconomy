package com.htmlman1.capitaleconomy.shop;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.htmlman1.capitaleconomy.util.ValidationUtils;

public class ShopSign {
	
	private UUID owner;
	private Material wareType;
	private double price;
	private Integer quantity;

	public ShopSign(Block b) {
		this((Sign) b.getState());
	}
	
	@SuppressWarnings("deprecation")
	public ShopSign(Sign sign) {
		OfflinePlayer owner = Bukkit.getOfflinePlayer(sign.getLine(1));
		double price = Double.parseDouble(sign.getLine(3));
		this.setOwner(owner.getUniqueId());
		this.setPrice(price);
	}

	public UUID getOwner() {
		return owner;
	}

	private void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Material getWareType() {
		return wareType;
	}

	private void setWareType(Material wareType) {
		this.wareType = wareType;
	}

	public double getPrice() {
		return price;
	}

    private void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;//Quantity available
    }

	private void setPrice(double price) {
		this.price = price;
	}

	public static boolean isValidSign(Block b) {
		if(!(b.getState() instanceof Sign)) return false;
		return isValidSign((Sign) b.getState());
	}
	
	private static boolean isValidSign(Sign sign) {
		if(sign.getLine(0).equalsIgnoreCase("[CapitalShop]")) {
			if(ValidationUtils.isDouble(sign.getLine(3))) return true;
		}
		return false;
	}
	public static boolean parseMaterialAndQuantity(Block block){
		Sign sign = (Sign)block.getState();//Only going to get passed sign blocks.
        if(sign.getLine(0).equalsIgnoreCase("[CapitalShop]")) {
            Material mat = Material.getMaterial(sign.getLine(2).split(":")[0]);
            int quantity = ValidationUtils.isInt(sign.getLine(2).split(":")[1]) ? Integer.parseInt(sign.getLine(2).split(":")[1]) : 0;
            return mat != null || quantity != 0;
        }
        return false;
    }

    public static boolean isNameRegistered(Block block) {
        return false;
    }
}
